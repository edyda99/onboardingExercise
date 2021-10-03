package com.eurisko.onboardingexercise.project.module.core.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CompanyResponseDto {
    private String name;
    private String catchPhrase;
    private String bs;
}
