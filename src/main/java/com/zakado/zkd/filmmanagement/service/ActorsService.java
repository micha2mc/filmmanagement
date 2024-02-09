package com.zakado.zkd.filmmanagement.service;


import com.zakado.zkd.filmmanagement.model.Actor;

import java.util.List;

public interface ActorsService {

    void deleteActor(Integer dni);

    Actor searchActorById(Integer id);

    Actor saveActor(Actor actorDTO);

    List<Actor> searchAllActors();
}
