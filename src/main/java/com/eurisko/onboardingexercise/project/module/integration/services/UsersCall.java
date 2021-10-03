package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;

import java.util.List;

public interface UsersCall {
    List<UserResponseDto> getAllUsers();
//    List<UserResponseDto> getAllUsers(Long id);
    void fillDb();
}
