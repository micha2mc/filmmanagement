package com.zakado.zkd.filmmanagement.model.dto;

import com.zakado.zkd.filmmanagement.model.entity.Movie;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class ActorRequest {

    private Integer nid;

    private String name;

    private String dni;

    private String dob;

    private String cob;

    private String image;

    private String status;

    private String genre;

    private Set<Movie> moviesEntities;
}
