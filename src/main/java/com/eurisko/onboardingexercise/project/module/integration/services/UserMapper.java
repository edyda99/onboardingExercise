package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import com.eurisko.onboardingexercise.project.module.integration.entities.User;
import com.eurisko.onboardingexercise.project.module.integration.entities.embedded.Address;
import com.eurisko.onboardingexercise.project.module.integration.entities.embedded.Company;
import com.eurisko.onboardingexercise.project.module.integration.entities.embedded.Geo;
import com.eurisko.onboardingexercise.project.module.integration.model.response.AddressResponse;
import com.eurisko.onboardingexercise.project.module.integration.model.response.CompanyResponse;
import com.eurisko.onboardingexercise.project.module.integration.model.response.GeoResponse;
import com.eurisko.onboardingexercise.project.module.integration.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.*;

@Mapper(unmappedTargetPolicy = WARN)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userToEntity(UserResponse userResponse);
    List<User> userToEntity(List<UserResponse> userResponse);

    UserResponseDto userToDto(User users);
    List<UserResponseDto> userToDto(List<User> users);
}
