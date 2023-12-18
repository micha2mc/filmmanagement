package com.zakado.zkd.filmmanagement.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class MoviesRequest {
    private Integer nid;
    private String title;
    private Integer year;
    private Integer duration;
    private String country;
    private String genre;

    private String synopsis;
    private String image;
    private String status;
    private Set<ActorRequest> actors;
}
