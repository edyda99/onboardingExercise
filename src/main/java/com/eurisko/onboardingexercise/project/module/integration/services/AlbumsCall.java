package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import reactor.core.publisher.Flux;

public interface AlbumsCall {
    Flux<AlbumResponseDto> getAllAlbums();

    void fillDb();
//    void fillDb();
}
