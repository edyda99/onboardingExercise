package com.eurisko.onboardingexercise.project.module.integration.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Role {
    @Id
    private Long id;
    private String name;
}