package com.eurisko.onboardingexercise.project.module.integration.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Accessors(chain = true)
public class Album implements Persistable<Long> {
    @Id
    private Long id;

    private String title;

    private Date date;

    @ManyToOne(fetch = EAGER/*{PERSIST,REFRESH}*/)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "album", fetch = EAGER, orphanRemoval = true)
    private Collection<Photo> photos = Collections.synchronizedSet(new HashSet<>());

    public Album addAlbum(Photo photo) {
        if (photo == null) return this;
        this.photos.add(photo);
        return this;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
