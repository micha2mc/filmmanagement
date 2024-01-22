package com.zakado.zkd.filmmanagement.dao;

import com.zakado.zkd.filmmanagement.model.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorsDAO {

    Actor updateActor(Actor actor);

    Actor saveActor(Actor actor);

    Actor searchActorById(Integer id);

    List<Actor> searchAllActors();

    List<Actor> searchMoviesByNameActor(String name);

    void deleteActor(Actor actor);
}
