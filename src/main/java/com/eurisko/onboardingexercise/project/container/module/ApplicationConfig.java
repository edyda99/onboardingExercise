package com.eurisko.onboardingexercise.project.container.module;

import com.eurisko.onboardingexercise.project.module.integration.util.ExerciseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

//    @Bean
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    @Bean
    WebClient webClient(ExerciseProperties exerciseProperties){
        return WebClient.create(exerciseProperties.getBaseUrl());
    }
}
