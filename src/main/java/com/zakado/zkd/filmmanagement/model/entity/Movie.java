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
    @Column(name = "genre")
    private String genre;
    @Basic
    @Column(name = "synopsis")
    private String synopsis;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "status")
    private String status;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movies_actors",
            joinColumns = {@JoinColumn(name = "id_movies_fk", referencedColumnName = "nid")},
            inverseJoinColumns = {@JoinColumn(name = "id_actors_fk", referencedColumnName = "nid")})
    @JsonIgnoreProperties("moviesEntities")
    @ToString.Exclude
    private Set<Actor> actors = new HashSet<>();


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

}
