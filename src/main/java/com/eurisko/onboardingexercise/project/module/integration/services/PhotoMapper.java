package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import com.eurisko.onboardingexercise.project.module.integration.entities.Photo;
import com.eurisko.onboardingexercise.project.module.integration.model.response.PhotoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(unmappedTargetPolicy = ERROR)
public interface PhotoMapper {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    @Mapping(target = "date",ignore = true)
    Photo photoToEntity(PhotoResponse response);
    Set<Photo> photoToEntity(Set<PhotoResponse> response);

    PhotoResponseDto photoToDto(Photo response);
    AlbumResponseDto photoToDto(Album album);
    Set<PhotoResponseDto> photoToDto(Set<Photo> response);
}
