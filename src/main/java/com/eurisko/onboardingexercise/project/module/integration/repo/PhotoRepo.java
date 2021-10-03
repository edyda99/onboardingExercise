package com.eurisko.onboardingexercise.project.module.integration.repo;

import com.eurisko.onboardingexercise.project.module.integration.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo,Long> {
}
