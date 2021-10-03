package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;

import java.util.Set;

public interface UsersCall {
    Set<UserResponseDto> getAllUsers();
//    List<UserResponseDto> getAllUsers(Long id);
    void fillDb();
}
