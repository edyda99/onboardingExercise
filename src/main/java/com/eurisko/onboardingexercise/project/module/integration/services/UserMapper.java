package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import com.eurisko.onboardingexercise.project.module.integration.entities.User;
import com.eurisko.onboardingexercise.project.module.integration.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(unmappedTargetPolicy = WARN)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userToEntity(UserResponse userResponse);
    Set<User> userToEntity(Set<UserResponse> userResponse);

    UserResponseDto userToDto(User users);
    Set<UserResponseDto> userToDto(Set<User> users);
}
