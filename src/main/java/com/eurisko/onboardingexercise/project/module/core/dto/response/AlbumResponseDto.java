package com.eurisko.onboardingexercise.project.module.core.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Data
@Getter
@Setter
public class AlbumResponseDto {
    private Long id;
    private String title;
    @JsonIgnore
    private String Date;
    private Collection<PhotoResponseDto> photos = Collections.synchronizedSet(new HashSet<>());
}
