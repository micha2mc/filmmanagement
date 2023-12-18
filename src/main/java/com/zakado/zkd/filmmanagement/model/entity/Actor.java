package com.zakado.zkd.filmmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "actors", schema = "moviesactorsdb")
@Builder
public class Actor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nid")
    private Integer nid;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "dob")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    @Basic
    @Column(name = "cob")
    private String cob;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "genre")
    private String genre;

    @ManyToMany(mappedBy = "actors",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnoreProperties("actors")
    private Set<Movie> moviesEntities = new HashSet<>();

    public void addMovies(Movie movie) {
        if (movie != null) {
            getMoviesEntities().add(movie);
        }
    }

    public void removeMovies(Movie movie) {
        if (movie != null) {
            getMoviesEntities().remove(movie);
        }
    }

}
