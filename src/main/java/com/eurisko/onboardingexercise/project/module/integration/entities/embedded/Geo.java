package com.eurisko.onboardingexercise.project.module.integration.entities.embedded;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Geo {
    private String lat;
    private String lng;
}
