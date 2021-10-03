package com.eurisko.onboardingexercise.project.module.core.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PhotoResponseDto {
    private Long id;
    private String title;
    private String url;
    private String thumbnailUrl;
    @JsonIgnore
    private String Date;
}
