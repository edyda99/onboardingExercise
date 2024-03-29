package com.eurisko.onboardingexercise.project.module.integration.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
