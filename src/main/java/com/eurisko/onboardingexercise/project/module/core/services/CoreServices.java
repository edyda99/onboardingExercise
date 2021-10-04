package com.eurisko.onboardingexercise.project.module.core.services;

import com.eurisko.onboardingexercise.project.module.core.dto.request.AlbumRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.request.PhotoRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoreServices {
    Flux<PhotoResponseDto> getAllPhotos();
    Flux<AlbumResponseDto> getAllAlbums();
    Flux<UserResponseDto> getAllUsers();

    Mono<UserResponseDto> getUserDetails(Long id);

    void createPhoto(PhotoRequestDto dto);
    void createAlbum(AlbumRequestDto dto);

    void updatePhoto(PhotoRequestDto dto);
    void updateAlbum(AlbumRequestDto dto);

    void deletePhoto(Long dto);
    void deleteAlbum(Long dto);
//
//    void fillDb();
}
