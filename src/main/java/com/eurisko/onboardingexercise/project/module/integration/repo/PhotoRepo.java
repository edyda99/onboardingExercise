package com.eurisko.onboardingexercise.project.module.integration.repo;

import com.eurisko.onboardingexercise.project.module.integration.entities.Photo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PhotoRepo extends ReactiveMongoRepository<Photo,Long> {
    Flux<Photo> findByAlbumId(Long id);
}
