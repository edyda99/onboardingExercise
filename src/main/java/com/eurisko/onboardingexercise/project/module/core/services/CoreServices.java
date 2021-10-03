package com.eurisko.onboardingexercise.project.module.core.services;

import com.eurisko.onboardingexercise.project.module.core.dto.request.AlbumRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.request.PhotoRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;

import java.util.List;

public interface CoreServices {
    List<PhotoResponseDto> getAllPhotos();
    List<AlbumResponseDto> getAllAlbums();
    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserDetails(Long id);

    void createPhoto(PhotoRequestDto dto);
    void createAlbum(AlbumRequestDto dto);

    void updatePhoto(PhotoRequestDto dto);
    void updateAlbum(AlbumRequestDto dto);

    void deletePhoto(Long dto);
    void deleteAlbum(Long dto);

    List<PhotoResponseDto> getPhotosSorted(Long id);
    List<AlbumResponseDto> getAlbumsSorted(Long id);

    void fillDb();
}
