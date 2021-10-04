package com.eurisko.onboardingexercise.project.module.integration.services.impl;

import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import com.eurisko.onboardingexercise.project.module.integration.entities.User;
import com.eurisko.onboardingexercise.project.module.integration.model.response.UserResponse;
import com.eurisko.onboardingexercise.project.module.integration.repo.AlbumRepo;
import com.eurisko.onboardingexercise.project.module.integration.repo.UserRepo;
import com.eurisko.onboardingexercise.project.module.integration.services.AlbumMapper;
import com.eurisko.onboardingexercise.project.module.integration.services.UserMapper;
import com.eurisko.onboardingexercise.project.module.integration.services.UsersCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@Log4j2
@RequiredArgsConstructor
public class UsersCallImpl implements UsersCall {
    private final WebClient webClient;
    private final UserMapper mapper = UserMapper.INSTANCE;
    private final UserRepo repo;
    private static Boolean flag = true;
    private final AlbumRepo albumRepo;
    private final AlbumMapper albumMapper = AlbumMapper.INSTANCE;

    @Override
    public Flux<UserResponseDto> getAllUsers() {
        if (flag) {
            flag = false;
            return webClient.get().uri("/users")
                    .retrieve()
                    .bodyToFlux(UserResponse.class)
                    .map(m->{
                        User user = mapper.userToEntity(m);
                        albumRepo.findByUserId(m.getId()).doOnNext(user::addAlbum);
                        return user;
                    })
                    .flatMap(repo::save)
                    .map(mapper::userToDto)
                    .doOnComplete(new Thread(()->log.info("finished importing users")));
        }
        return repo
                .findAll()
                .map(mapper::userToDto);
    }

    @Override
    public void fillDb() {
        webClient.get().uri("/users")
                .retrieve()
                .bodyToFlux(UserResponse.class)
                .map(mapper::userToEntity)
                .flatMap(repo::save)
                .doOnComplete(new Thread(()->log.info("finished importing users")));
        repo.findAll().toStream().forEach(p->p.setAlbums(albumRepo.findByUserId(p.getId()).collectList().block()));
    }
}