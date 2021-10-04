package com.eurisko.onboardingexercise.project.module.integration.services.impl;

import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import com.eurisko.onboardingexercise.project.module.integration.model.response.AlbumResponse;
import com.eurisko.onboardingexercise.project.module.integration.repo.AlbumRepo;
import com.eurisko.onboardingexercise.project.module.integration.repo.PhotoRepo;
import com.eurisko.onboardingexercise.project.module.integration.services.AlbumMapper;
import com.eurisko.onboardingexercise.project.module.integration.services.AlbumsCall;
import com.eurisko.onboardingexercise.project.module.integration.services.PhotoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Log4j2
public class AlbumsCallImpl implements AlbumsCall {

    private final AlbumRepo repo;
    private final AlbumMapper mapper = AlbumMapper.INSTANCE;
    private final WebClient webClient;
    private static Boolean flag = true;
    private final PhotoRepo photoRepo;
    private final PhotoMapper photoMapper = PhotoMapper.INSTANCE;

    @Override
    public Flux<AlbumResponseDto> getAllAlbums() {
        if (flag) {
            flag = false;
            return webClient.get().uri("/albums")
                    .retrieve()
                    .bodyToFlux(AlbumResponse.class)
//                    .doOnNext(u -> userRepo.save(new User().setId(u.getUserId()).addAlbum(mapper.albumEntity(u))))
                    .map(m->{
                        Album album = mapper.albumEntity(m);
                        photoRepo.findByAlbumId(m.getId()).doOnNext(album::addPhoto);
                        return album;
                    })
                    .flatMap(repo::save)
                    .map(mapper::albumDto)
                    .doOnComplete(new Thread(()->log.info("finished importing albums")));
        }
        return repo.findAll()
                .map(mapper::albumDto);
    }

    @Override
    public void fillDb() {
        webClient.get().uri("/albums")
                .retrieve()
                .bodyToFlux(AlbumResponse.class)
                .map(m->{
                    Album album = mapper.albumEntity(m);
                    photoRepo.findByAlbumId(m.getId()).doOnNext(album::addPhoto);
                    return album;
                })
                .flatMap(repo::save)
                .doOnComplete(new Thread(()->log.info("finished importing albums")));
        repo.findAll().toStream().forEach(p->p.setPhotos(photoRepo.findByAlbumId(p.getId()).collectList().block()));
    }
}
