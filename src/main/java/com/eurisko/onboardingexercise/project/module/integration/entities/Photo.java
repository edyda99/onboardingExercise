package com.eurisko.onboardingexercise.project.module.integration.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter

@Accessors(chain = true)
@NoArgsConstructor
public class Photo  implements Persistable<Long> {
    @Id
    private Long id;

    private String title;

    private String url;

    private String thumbnailUrl;

    private Date date;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "albumId",referencedColumnName = "id")
    private Album album;

    @Override
    public boolean isNew() {
        return false;
    }
}
