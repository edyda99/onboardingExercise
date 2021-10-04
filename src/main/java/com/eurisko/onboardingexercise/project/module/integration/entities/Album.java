package com.eurisko.onboardingexercise.project.module.integration.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


@Document
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

    private Long userId;

    private Collection<Photo> photos = Collections.synchronizedSet(new HashSet<>());

    public Album addPhoto(Photo photo) {
        if (photo == null) return this;
        this.photos.add(photo);
        return this;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
