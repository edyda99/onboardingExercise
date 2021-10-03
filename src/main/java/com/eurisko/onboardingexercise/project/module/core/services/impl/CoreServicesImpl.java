package com.eurisko.onboardingexercise.project.module.core.services.impl;

import com.eurisko.onboardingexercise.project.module.core.dto.request.AlbumRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.request.PhotoRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import com.eurisko.onboardingexercise.project.module.core.exceptions.DbException;
import com.eurisko.onboardingexercise.project.module.core.services.CoreServices;
import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import com.eurisko.onboardingexercise.project.module.integration.entities.Photo;
import com.eurisko.onboardingexercise.project.module.integration.entities.User;
import com.eurisko.onboardingexercise.project.module.integration.repo.AlbumRepo;
import com.eurisko.onboardingexercise.project.module.integration.repo.PhotoRepo;
import com.eurisko.onboardingexercise.project.module.integration.repo.UserRepo;
import com.eurisko.onboardingexercise.project.module.integration.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CoreServicesImpl implements CoreServices {
    private final AlbumsCall albumsCall;
    private final PhotosCall photosCall;
    private final UsersCall usersCall;

    private final AlbumRepo albumRepo;
    private final PhotoRepo photoRepo;
    private final UserRepo userRepo;

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PhotoMapper photoMapper = PhotoMapper.INSTANCE;
    private final AlbumMapper albumMapper = AlbumMapper.INSTANCE;

    @Override
    public List<PhotoResponseDto> getAllPhotos() {
        return photosCall.getAllPhotos();
    }

    @Override
    public List<AlbumResponseDto> getAllAlbums() {
        return albumsCall.getAllAlbums();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return usersCall.getAllUsers();
    }

    @Override
    public UserResponseDto getUserDetails(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            log.error("User not found in Db for id: {}", id);
            throw new DbException("User not found in Db for id: "+id);
        }
        UserResponseDto response = userMapper.userToDto(user.get());
        response.getAlbums().stream().sorted(Comparator.comparing(AlbumResponseDto::getDate))
                .forEach(p->p.getPhotos().stream()
                        .sorted(Comparator.comparing(PhotoResponseDto::getDate)));
                return response;
    }

    @Override
    @Transactional
    public void createAlbum(AlbumRequestDto dto) {
        Optional<User> user = userRepo.findById(dto.getUserId());
        Album album = new Album()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setDate(new Date(System.currentTimeMillis()));
        if (user.isEmpty())
            log.warn("Album updated with id {} pinned the userId to an invalid user", dto.getUserId());
        else album.setUser(user.get());
        albumRepo.save(album);
    }

    @Override
    @Transactional
    public void createPhoto(PhotoRequestDto dto) {
        Optional<Album> album = albumRepo.findById(dto.getAlbumId());
        Photo photo = new Photo()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setThumbnailUrl(dto.getThumbnailUrl())
                .setUrl(dto.getUrl())
                .setDate(new Date(System.currentTimeMillis()));
        if (album.isEmpty())
            log.warn("Photo updated with id {} pinned the albumId to an invalid album", dto.getAlbumId());
        else photo.setAlbum(album.get());
        photoRepo.save(photo);
    }

    @Override
    @Transactional
    public void updatePhoto(PhotoRequestDto dto) {
        Optional<Photo> photo = photoRepo.findById(Objects.requireNonNull(dto.getId()));
        if (photo.isEmpty()) {
            log.error("Photo Not Found");
            throw new DbException("Photo not found in the database");
        }
        photo.get()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setUrl(dto.getUrl())
                .setThumbnailUrl(dto.getThumbnailUrl())
                .setDate(new Date(System.currentTimeMillis()));

        Optional<Album> album = albumRepo.findById(dto.getAlbumId());
        if (album.isEmpty())
            log.warn("Photo updated with id {} pinned the albumId to an invalid album", dto.getAlbumId());
        else photo.get().setAlbum(album.get());
        photoRepo.save(photo.get());
    }

    @Override
    @Transactional
    public void updateAlbum(AlbumRequestDto dto) {
        Optional<Album> album = albumRepo.findById(Objects.requireNonNull(dto.getId()));

        if (album.isEmpty()) {
            log.error("Album Not Found");
            throw new DbException("Album not found in the database");
        }
        album.get()
                .setTitle(dto.getTitle())
                .setDate(new Date(System.currentTimeMillis()));
        Optional<User> user = userRepo.findById(dto.getUserId());
        if (user.isEmpty())
            log.warn("Album updated with id {} pinned the userId to an invalid user", dto.getUserId());
        else album.get().setUser(user.get());
        albumRepo.save(album.get());
    }

    @Override
    @Transactional
    public void deletePhoto(Long dto) {
        Optional<Photo> photo = photoRepo.findById(dto);
        photo.ifPresent(photoRepo::delete);
    }

    @Override
    @Transactional
    public void deleteAlbum(Long dto) {
        Optional<Album> album = albumRepo.findById(dto);
        album.ifPresent(p->{
            photoRepo.deleteAll(p.getPhotos());
            albumRepo.delete(p);
        });
    }

    @Override
    public List<PhotoResponseDto> getPhotosSorted(Long id) {
        return photosCall.getAllPhotos();
    }

    @Override
    public List<AlbumResponseDto> getAlbumsSorted(Long id) {
        return null;
    }

    @Override
    public void fillDb() {
        usersCall.fillDb();
        albumsCall.fillDb();
        photosCall.fillDb();
        log.info("Finished importing db");
//        IntStream.range(0, photos.size()).parallel().forEach(p -> {
//            photos.get(p).getAlbum().getPhotos().add(photos.get(p));
//            photos.get(p).setAlbum(null);
//        });

//        IntStream.range(0, albums.size()).parallel().forEach(p -> {
//            albums.get(p).getUser().getAlbums().add(albums.get(p));
//            albums.get(p).setUser(null);
//        });

    }
}
