package com.eurisko.onboardingexercise.project.module.integration.services.impl;

import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.core.exceptions.DbException;
import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import com.eurisko.onboardingexercise.project.module.integration.entities.Photo;
import com.eurisko.onboardingexercise.project.module.integration.model.response.PhotoResponse;
import com.eurisko.onboardingexercise.project.module.integration.repo.AlbumRepo;
import com.eurisko.onboardingexercise.project.module.integration.repo.PhotoRepo;
import com.eurisko.onboardingexercise.project.module.integration.services.PhotoMapper;
import com.eurisko.onboardingexercise.project.module.integration.services.PhotosCall;
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
public class PhotosCallImpl implements PhotosCall {
    private final ExerciseProperties properties;
    private final PhotoRepo repo;
    private final PhotoMapper mapper = PhotoMapper.INSTANCE;
    private final RestTemplate restTemplate;
    private final AlbumRepo albumRepo;

    @Override
    public List<PhotoResponseDto> getAllPhotos() {
        List<Photo> list = repo.findAll();
        if(list.isEmpty()) {
            List<PhotoResponse> photos = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(properties.getBaseUrl() + "/photos", PhotoResponse[].class)));
            List<Photo> photos1 = new ArrayList<>();
            photos.forEach(p->{
                Optional<Album> album = albumRepo.findById(p.getAlbumId());
                if(album.isEmpty()) {
                    log.error("Album is not found with id: {}", p.getAlbumId());
                    throw new DbException("Album is not found with id: " + p.getAlbumId());
                }
                photos1.add(mapper.photoToEntity(p).setAlbum(album.get()));
            });
            repo.saveAll(photos1);
            return mapper.photoToDto(photos1);
        }
        return mapper.photoToDto(list);
    }

    @Override
    @Transactional
    public void fillDb() {
        List<Photo> list = repo.findAll();
        if(list.isEmpty()) {
            List<PhotoResponse> photos = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(properties.getBaseUrl() + "/photos", PhotoResponse[].class)));
            List<Photo> photos1 = new ArrayList<>();
            photos.forEach(p->{
                Optional<Album> album = albumRepo.findById(p.getAlbumId());
                if(album.isEmpty()) {
                    log.error("Album is not found with id: {}", p.getAlbumId());
                    throw new DbException("Album is not found with id: " + p.getAlbumId());
                }
                photos1.add(mapper.photoToEntity(p).setAlbum(album.get()).setDate(new Date(System.currentTimeMillis())));
            });
            repo.saveAll(photos1);
        }
    }
}
