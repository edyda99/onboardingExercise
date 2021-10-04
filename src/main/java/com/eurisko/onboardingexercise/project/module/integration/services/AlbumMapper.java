package com.eurisko.onboardingexercise.project.module.integration.services;

import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import com.eurisko.onboardingexercise.project.module.integration.model.response.AlbumResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(unmappedTargetPolicy = WARN)
public interface AlbumMapper {
    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    @Mapping(target = "date",ignore = true)
    Album albumEntity(AlbumResponse response);

    @Mapping(target = "photos",ignore = true)
    AlbumResponseDto albumDto(Album albums);
}
