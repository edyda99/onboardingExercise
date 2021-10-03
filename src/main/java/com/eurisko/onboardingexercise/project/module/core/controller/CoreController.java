package com.eurisko.onboardingexercise.project.module.core.controller;

import com.eurisko.onboardingexercise.project.module.core.dto.request.AlbumRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.request.PhotoRequestDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.AlbumResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.PhotoResponseDto;
import com.eurisko.onboardingexercise.project.module.core.dto.response.UserResponseDto;
import com.eurisko.onboardingexercise.project.module.core.services.CoreServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CoreController {
    private final CoreServices services;

    @GetMapping("/get-user-details")
    public UserResponseDto getUserDetails(@RequestParam Long id){return services.getUserDetails(id);}

    @GetMapping("/get-all-photos")
    public Set<PhotoResponseDto> getAllPhotos(){return services.getAllPhotos();}

    @GetMapping("/get-all-albums")
    public Set<AlbumResponseDto> getAllAlbums(){return services.getAllAlbums();}

    @GetMapping("/get-all-users")
    public Set<UserResponseDto> getAllUsers(){return services.getAllUsers();}

    @PostMapping("/create-photo")
    public void createPhoto(@RequestBody PhotoRequestDto dto){services.createPhoto(dto);}

    @PostMapping("/create-album")
    public void createAlbum(@RequestBody AlbumRequestDto dto){services.createAlbum(dto);}

    @PostMapping("/update-photo")
    public void updatePhoto(@RequestBody PhotoRequestDto dto){services.updatePhoto(dto);}

    @PostMapping(value = "/update-album",consumes = APPLICATION_JSON_VALUE)
    public void updateAlbum(@RequestBody AlbumRequestDto dto){services.updateAlbum(dto);}

    @PostMapping("/delete-photo")
    public void deletePhoto(@RequestParam Long id){services.deletePhoto(id);}

    @PostMapping("/delete-album")
    public void deleteAlbum(@RequestParam Long id){services.deleteAlbum(id);}
}
