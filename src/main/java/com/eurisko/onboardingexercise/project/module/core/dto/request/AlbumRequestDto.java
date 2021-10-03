package com.eurisko.onboardingexercise.project.module.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AlbumRequestDto {
    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    private String title;
}
