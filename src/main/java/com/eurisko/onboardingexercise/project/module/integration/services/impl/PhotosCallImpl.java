package com.eurisko.onboardingexercise.project.module.integration.services.impl;

import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.integration.entities.Photo;
import com.eurisko.onboardingexercise.project.module.integration.model.response.PhotoResponse;
import com.eurisko.onboardingexercise.project.module.integration.repo.AlbumRepo;
import com.eurisko.onboardingexercise.project.module.integration.repo.PhotoRepo;
import com.eurisko.onboardingexercise.project.module.integration.services.PhotoMapper;
import com.eurisko.onboardingexercise.project.module.integration.services.PhotosCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class PhotosCallImpl implements PhotosCall {
    private final PhotoRepo repo;
    private final PhotoMapper mapper = PhotoMapper.INSTANCE;
    private final WebClient webClient;
    private static Boolean flag = true;
    private final AlbumRepo albumRepo;

    @Override
    public Flux<PhotoResponseDto> getAllPhotos() {
        Flux<Photo> photoFlux = repo.findAll();
        if (flag) {
            flag = false;
            return webClient.get().uri("/photos")
                    .retrieve()
                    .bodyToFlux(PhotoResponse.class)
//                    .doOnNext(u -> albumRepo.save(new Album().setId(u.getAlbumId()).addPhoto(mapper.photoToEntity(u))))
                    .map(mapper::photoToEntity)
                    .flatMap(repo::save)
                    .map(mapper::photoToDto)
                    .doOnComplete(new Thread(()->log.info("finished importing photos")));
        }
        return photoFlux
                .map(mapper::photoToDto);
    }

    @Override
    public void fillDb() {
        webClient.get().uri("/photos")
                .retrieve()
                .bodyToFlux(PhotoResponse.class)
//                    .doOnNext(u -> albumRepo.save(new Album().setId(u.getAlbumId()).addPhoto(mapper.photoToEntity(u))))
                .map(mapper::photoToEntity)
                .flatMap(repo::save)
                .then(Mono.just("finished importing photos"));
    }
}
