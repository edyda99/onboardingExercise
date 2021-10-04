package com.eurisko.onboardingexercise.project.module.integration.repo;

import com.eurisko.onboardingexercise.project.module.integration.entities.LoginUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LoginUserRepo extends ReactiveMongoRepository<LoginUser,Long> {
    LoginUser findByUsername(String username);
}
