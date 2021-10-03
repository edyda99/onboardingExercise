package com.eurisko.onboardingexercise.project.module.integration.repo;

import com.eurisko.onboardingexercise.project.module.integration.entities.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRepo extends JpaRepository<LoginUser,Long> {
    LoginUser findByUsername(String username);
}
