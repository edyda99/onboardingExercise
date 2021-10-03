package com.eurisko.onboardingexercise.project.module.core.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GeoResponseDto {
    private String lat;
    private String lng;
}
