package com.eurisko.onboardingexercise.project.module.core.controller;

import com.eurisko.onboardingexercise.project.module.core.dto.request.AlbumRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.request.PhotoRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import com.eurisko.onboardingexercise.project.module.core.services.CoreServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CoreController {
    private final CoreServices services;

    @GetMapping(value = "/get-user-details")
    public Mono<UserResponseDto> getUserDetails(@RequestParam Long id){return services.getUserDetails(id);}

    @GetMapping(value = "/get-all-photos", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<PhotoResponseDto> getAllPhotos(){return services.getAllPhotos();}

    @GetMapping(value = "/get-all-albums", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AlbumResponseDto> getAllAlbums(){return services.getAllAlbums();}

    @GetMapping(value = "/get-all-users", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<UserResponseDto> getAllUsers(){return services.getAllUsers();}

    @PostMapping("/create-photo")
    public void createPhoto(@RequestBody PhotoRequestDto dto){services.createPhoto(dto);}

    @PostMapping("/create-album")
    public void createAlbum(@RequestBody AlbumRequestDto dto){services.createAlbum(dto);}

    @PostMapping("/update-photo")
    public void updatePhoto(@RequestBody PhotoRequestDto dto){services.updatePhoto(dto);}

    @PostMapping(value = "/update-album",consumes = APPLICATION_JSON_VALUE)
    public void updateAlbum(@RequestBody AlbumRequestDto dto){services.updateAlbum(dto);}

    @PostMapping("/delete-photo/{id}")
    public void deletePhoto(@RequestParam Long id){services.deletePhoto(id);}

    @PostMapping("/delete-album/{id}")
    public void deleteAlbum(@RequestParam Long id){services.deleteAlbum(id);}
}
