package com.eurisko.onboardingexercise.project.module.core.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressResponseDto {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoResponseDto geo;
}
