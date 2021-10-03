package com.eurisko.onboardingexercise.project.container.module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
