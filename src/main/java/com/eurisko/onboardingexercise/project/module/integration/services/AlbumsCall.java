package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;

import java.util.List;

public interface AlbumsCall {
    List<AlbumResponseDto> getAllAlbums();
    void fillDb();
}
