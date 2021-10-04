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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Flux<PhotoResponseDto> getAllPhotos() {return photosCall.getAllPhotos();}

    @Override
    public Flux<AlbumResponseDto> getAllAlbums() {
        return albumsCall.getAllAlbums();
    }

    @Override
    public Flux<UserResponseDto> getAllUsers() {
        return usersCall.getAllUsers();
    }

    @Override
    public Mono<UserResponseDto> getUserDetails(Long id) {
        Mono<User> user = userRepo.findById(id);
//        user.switchIfEmpty({
//            Mono.error(new DbException("User not found in Db"));
//            return new UserResponseDto();
//        })
        Mono<UserResponseDto> response = user.flatMap(p->Mono.just(userMapper.userToDto(p)));
        response = response.flatMap(p->Mono.just(p.setAlbums(p.getAlbums().stream().sorted(Comparator.nullsLast(
                Comparator.comparing(AlbumResponseDto::getDate))).collect(Collectors.toList()))));

                    return response;
    }

    @Override
    @Transactional
    public void createAlbum(AlbumRequestDto dto) {
        if(dto.getId() == null) throw new DbException("Please provide an Id");
        if(dto.getUserId() == null) throw new DbException("Please provide an User Id");

//        if(user.isEmpty()) throw new DbException("this user with id: " + dto.getUserId() + " doesn't exist");

        Album album = new Album()
                .setUserId(dto.getUserId())
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setDate(new Date(System.currentTimeMillis()));
        Mono<User> mono = userRepo.findById(dto.getUserId()).doOnNext(p->{
            log.info("10452");
            p.addAlbum(album);
        });
        mono.flatMap(userRepo::save);
        albumRepo.save(album);
    }

    @Override
    @Transactional
    public void createPhoto(PhotoRequestDto dto) {
        if(dto.getId() == null) throw new DbException("Please provide an Id");
        if(dto.getAlbumId() == null) throw new DbException("Please provide an User Id");

        Mono<Album> album = albumRepo.findById(dto.getAlbumId());
//        if (album.isEmpty()){
//            log.warn("Photo updated with id {} pinned the albumId to an invalid album", dto.getAlbumId());
//            throw new DbException("Photo updated with id: " + dto.getAlbumId() + " pinned the albumId to an invalid album");
//        }

        Photo photo = new Photo()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setThumbnailUrl(dto.getThumbnailUrl())
                .setUrl(dto.getUrl())
                .setDate(new Date(System.currentTimeMillis()));
        album.map(p->p.addPhoto(photo));

        album.flatMap(albumRepo::save);
        photoRepo.save(photo);
    }

    @Override
    @Transactional
    public void updatePhoto(PhotoRequestDto dto) {
        Mono<Photo> photo = photoRepo.findById(Objects.requireNonNull(dto.getId()));

            log.error("Photo Not Found");
            throw new DbException("Photo not found in the database");

        photo.map(p->p
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setUrl(dto.getUrl())
                .setThumbnailUrl(dto.getThumbnailUrl())
                .setDate(new Date(System.currentTimeMillis())))
                .onErrorResume((ex)->{
                    log.error(ex.getMessage());
                    throw new DbException("Photo not found in the database" + ex.getMessage());
                });

        Mono<Album> album = albumRepo.findById(dto.getAlbumId());
        Mono.zip(photo,album,(photoZ,albumZ)->albumZ.addPhoto(photoZ));

        photo.flatMap(photoRepo::save).onErrorResume(e-> {
            log.error(e.getMessage());
           return Mono.error(new DbException("Photo updated with id {} pinned the albumId to an invalid album"));
        });
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
        photoRepo.deleteById(dto).onErrorResume(e-> {
            log.error(e.getMessage());
            return Mono.error(new DbException(e.getMessage()));
        });
    }

    @Override
    @Transactional
    public void deleteAlbum(Long dto) {
     albumRepo.deleteById(dto).onErrorResume(e -> {
            log.error(e.getMessage());
            return Mono.error(new DbException(e.getMessage()));
        });
    }
//
//    @Override
//    public void fillDb() {
//        photosCall.fillDb();
//        albumsCall.fillDb();
//        usersCall.fillDb();
//        log.info("Finished importing db");
////        IntStream.range(0, photos.size()).parallel().forEach(p -> {
////            photos.get(p).getAlbum().getPhotos().add(photos.get(p));
////            photos.get(p).setAlbum(null);
////        });
//
////        IntStream.range(0, albums.size()).parallel().forEach(p -> {
////            albums.get(p).getUser().getAlbums().add(albums.get(p));
////            albums.get(p).setUser(null);
////        });
//
//    }
}
