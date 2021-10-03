package com.eurisko.onboardingexercise.project.module.integration.model.response;

import com.eurisko.onboardingexercise.project.module.integration.entities.Album;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PhotoResponse {
    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
    private Album album;
}
