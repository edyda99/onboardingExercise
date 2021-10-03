package com.eurisko.onboardingexercise.project.module.integration.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GeoResponse {
    private String lat;
    private String lng;
}
