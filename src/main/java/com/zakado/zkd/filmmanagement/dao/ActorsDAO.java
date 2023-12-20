package com.zakado.zkd.filmmanagement.dao;

import com.zakado.zkd.filmmanagement.model.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorsDAO {

    Actor updateActor(Actor actor);

    //String eliminarActor(Integer dni);

    Actor saveActor(Actor actor);

    Optional<Actor> searchActorById(Integer id);

    List<Actor> searchAllActors();

    List<Actor> searchMoviesByNameActor(String name);

    void deleteActor(Actor actor);
}
