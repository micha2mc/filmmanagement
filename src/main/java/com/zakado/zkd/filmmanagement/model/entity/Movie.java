package com.zakado.zkd.filmmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies", schema = "moviesactorsdb")
@Builder
public class Movie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nid")
    private Integer nid;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "year")
    private Integer year;
    @Basic
    @Column(name = "duration")
    private Integer duration;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "synopsis")
    private String synopsis;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "youtubeid")
    private String youtubeTrailerId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_actors",
            joinColumns = {@JoinColumn(name = "id_movies_fk", referencedColumnName = "nid")},
            inverseJoinColumns = {@JoinColumn(name = "id_actors_fk", referencedColumnName = "nid")})
    @JsonIgnoreProperties("moviesEntities")
    @ToString.Exclude
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movies_genres",
            joinColumns = {@JoinColumn(name = "id_movies_fk", referencedColumnName = "nid")},
            inverseJoinColumns = {@JoinColumn(name = "id_genres_fk", referencedColumnName = "nid")})
    @JsonIgnoreProperties("moviesEntities")
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();


    public void addActor(Actor actor) {
        if (actor != null) {
            getActors().add(actor);
        }
    }

    public void removeActor(Actor actor) {
        if (actor != null) {
            getActors().remove(actor);
        }
    }

    public void addGenre(Genre genre){
        if (genre != null) {
            getGenres().add(genre);
        }
    }

    public void removeGenre(Genre genre){
        if (genre != null) {
            getGenres().remove(genre);
        }
    }

}
