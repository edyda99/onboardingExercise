package com.eurisko.onboardingexercise.project.module.integration.entities;

import com.eurisko.onboardingexercise.project.module.integration.entities.embedded.Address;
import com.eurisko.onboardingexercise.project.module.integration.entities.embedded.Company;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


@Document
@Getter
@Setter
@Accessors(chain = true)
public class User implements Persistable<Long> {
    @Id
    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
    //@JoinColumn(name = "userId");
    private Collection<Album> albums = Collections.synchronizedSet(new HashSet<>());

    public User addAlbum(Album album){
        if(album==null) return this;
        albums.add(album);
        return this;
    }
    @Override
    public boolean isNew() {
        return false;
    }
}
