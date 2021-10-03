package com.eurisko.onboardingexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAsync
public class OnboardingExerciseApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OnboardingExerciseApplication.class, args);
    }
}

