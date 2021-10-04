package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import reactor.core.publisher.Flux;


public interface PhotosCall {
    Flux<PhotoResponseDto> getAllPhotos();

    void fillDb();
}
