package com.eurisko.onboardingexercise.project.module.integration.repo;

import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AlbumRepo extends ReactiveMongoRepository<Album,Long> {
    Flux<Album> findByUserId(Long id);
}
