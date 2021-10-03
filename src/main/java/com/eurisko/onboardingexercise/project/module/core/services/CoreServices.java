package com.eurisko.onboardingexercise.project.module.core.services;

import com.eurisko.onboardingexercise.project.module.core.dto.request.AlbumRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.request.PhotoRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;

import java.util.Set;

public interface CoreServices {
    Set<PhotoResponseDto> getAllPhotos();
    Set<AlbumResponseDto> getAllAlbums();
    Set<UserResponseDto> getAllUsers();

    UserResponseDto getUserDetails(Long id);

    void createPhoto(PhotoRequestDto dto);
    void createAlbum(AlbumRequestDto dto);

    void updatePhoto(PhotoRequestDto dto);
    void updateAlbum(AlbumRequestDto dto);

    void deletePhoto(Long dto);
    void deleteAlbum(Long dto);

    void fillDb();
}
