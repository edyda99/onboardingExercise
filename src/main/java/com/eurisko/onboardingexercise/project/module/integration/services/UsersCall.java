package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import reactor.core.publisher.Flux;

public interface UsersCall {
    Flux<UserResponseDto> getAllUsers();
//    List<UserResponseDto> getAllUsers(Long id);
    void fillDb();
}
