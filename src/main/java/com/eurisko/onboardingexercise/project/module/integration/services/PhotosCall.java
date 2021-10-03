package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;

import java.util.Set;


public interface PhotosCall {
    Set<PhotoResponseDto> getAllPhotos();
    void fillDb();
}
