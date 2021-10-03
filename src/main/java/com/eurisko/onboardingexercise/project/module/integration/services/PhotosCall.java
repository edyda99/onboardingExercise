package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;

import java.util.List;


public interface PhotosCall {
    List<PhotoResponseDto> getAllPhotos();
    void fillDb();
}
