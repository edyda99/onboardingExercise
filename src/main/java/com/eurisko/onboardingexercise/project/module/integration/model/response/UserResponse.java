package com.eurisko.onboardingexercise.project.module.integration.model.response;

import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Data
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private AddressResponse address;
    private String phone;
    private String website;
    private CompanyResponse company;
    private Collection<Album> albums = Collections.synchronizedSet(new HashSet<>());
}
