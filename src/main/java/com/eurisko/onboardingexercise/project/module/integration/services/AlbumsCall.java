package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;

import java.util.Set;

public interface AlbumsCall {
    Set<AlbumResponseDto> getAllAlbums();
    void fillDb();
}
