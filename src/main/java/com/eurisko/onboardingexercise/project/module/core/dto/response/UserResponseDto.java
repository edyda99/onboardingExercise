package com.eurisko.onboardingexercise.project.module.core.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Data
@Getter
@Setter
@Accessors(chain = true)
public class UserResponseDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private AddressResponseDto address;
    private String phone;
    private String website;
    private CompanyResponseDto company;
    private Collection<AlbumResponseDto> albums = Collections.synchronizedSet(new HashSet<>());
}
