package com.eurisko.onboardingexercise.project.module.integration.repo;

import com.eurisko.onboardingexercise.project.module.integration.entities.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RoleRepo extends ReactiveMongoRepository<Role,Long> {
}
