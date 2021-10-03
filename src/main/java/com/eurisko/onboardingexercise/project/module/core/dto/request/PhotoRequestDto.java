package com.eurisko.onboardingexercise.project.module.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PhotoRequestDto {
    private Long id;
    @JsonProperty("album_id")
    private Long albumId;
    private String title;
    private String url;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
}
