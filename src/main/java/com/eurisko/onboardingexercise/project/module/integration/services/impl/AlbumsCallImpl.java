package com.eurisko.onboardingexercise.project.module.integration.services.impl;

import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.core.exceptions.DbException;
import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import com.eurisko.onboardingexercise.project.module.integration.entities.User;
import com.eurisko.onboardingexercise.project.module.integration.model.response.AlbumResponse;
import com.eurisko.onboardingexercise.project.module.integration.repo.AlbumRepo;
import com.eurisko.onboardingexercise.project.module.integration.repo.UserRepo;
import com.eurisko.onboardingexercise.project.module.integration.services.AlbumMapper;
import com.eurisko.onboardingexercise.project.module.integration.services.AlbumsCall;
import com.eurisko.onboardingexercise.project.module.integration.util.ExerciseProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class AlbumsCallImpl implements AlbumsCall {

    private final ExerciseProperties properties;
    private final AlbumRepo repo;
    private final UserRepo userRepo;
    private final AlbumMapper mapper = AlbumMapper.INSTANCE;
    private final RestTemplate restTemplate;

    @Override
    public Set<AlbumResponseDto> getAllAlbums() {
        Set<Album> list = new LinkedHashSet<>(repo.findAll());
        if (list.isEmpty()) {
            Set<AlbumResponse> albums = new LinkedHashSet<>(Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(properties.getBaseUrl() + "/albums", AlbumResponse[].class))));
            Set<Album> albums1 = new LinkedHashSet<>();
            albums.forEach(p -> {
                Optional<User> user = userRepo.findById(p.getUserId());
                if (user.isEmpty()) {
                    log.error("User is not found with id: {}", p.getUserId());
                    throw new DbException("User is not found with id: "+p.getUserId());//TODO create UserNotFoundException and handle it;
                }
                albums1.add(mapper.albumEntity(p).setUser(user.get()));
            });
            repo.saveAll(albums1);
            return mapper.albumToDto(albums1);
        }
        return mapper.albumToDto(list);
    }

    @Override
    @Transactional
    public void fillDb() {
        Set<Album> list = new LinkedHashSet<>(repo.findAll());
        if (list.isEmpty()) {
            Set<AlbumResponse> albums = new LinkedHashSet<>(Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(properties.getBaseUrl() + "/albums", AlbumResponse[].class))));
            Set<Album> albums1 = new HashSet<>();
            albums.forEach(p -> {
                Optional<User> user = userRepo.findById(p.getUserId());
                if (user.isEmpty()) {
                    log.error("User is not found with id: {}", p.getUserId());
                    throw new DbException("User is not found with id: "+ p.getUserId());
                }
                albums1.add(mapper.albumEntity(p).setUser(user.get()).setDate(new Date(System.currentTimeMillis())));
            });
            repo.saveAll(albums1);
        }
    }
}
