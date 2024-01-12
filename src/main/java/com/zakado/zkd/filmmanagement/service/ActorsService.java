package com.zakado.zkd.filmmanagement.service;

import com.zakado.zkd.filmmanagement.model.entity.Actor;

import java.util.List;

public interface ActorsService {
    //ActorRequest searchActorByDNI(String dni);

    Actor updateActor(Actor actorDTO);

    void deleteActor(Integer dni);

    Actor searchActorById(Integer id);

    Actor saveActor(Actor actorDTO);

    List<Actor> searchAllActors();
}
