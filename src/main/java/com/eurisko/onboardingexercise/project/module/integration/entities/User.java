package com.eurisko.onboardingexercise.project.module.integration.entities;

import com.eurisko.onboardingexercise.project.module.integration.entities.embedded.Address;
import com.eurisko.onboardingexercise.project.module.integration.entities.embedded.Company;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User implements Persistable<Long> {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Embedded
    private Address address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "website")
    private String website;
    @Embedded
    private Company company;
    @OneToMany(orphanRemoval = true,mappedBy = "user"/*{PERSIST,REFRESH}*/,fetch = EAGER)
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
