package com.eurisko.onboardingexercise.project.module.integration.model.response;

import com.eurisko.onboardingexercise.project.module.integration.entities.Photo;
import com.eurisko.onboardingexercise.project.module.integration.entities.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponse {
    private Long id;
    private Long userId;
    private String title;
    private User user;
    private Collection<Photo> photos = Collections.synchronizedSet(new HashSet<>());
}
