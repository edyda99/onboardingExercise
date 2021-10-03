package com.eurisko.onboardingexercise.project.module.integration.entities.embedded;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "Company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Column(name = "companyname")
    private String name;
    @Column(name = "catchPhrase")
    private String catchPhrase;
    @Column(name = "bs")
    private String bs;
}
