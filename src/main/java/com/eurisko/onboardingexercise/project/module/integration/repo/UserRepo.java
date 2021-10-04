package com.eurisko.onboardingexercise.project.module.integration.repo;

import com.eurisko.onboardingexercise.project.module.integration.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepo extends ReactiveMongoRepository<User,Long> {
}
