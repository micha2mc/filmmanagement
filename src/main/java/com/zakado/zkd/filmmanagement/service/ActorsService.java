package com.zakado.zkd.filmmanagement.service;

import com.zakado.zkd.filmmanagement.model.dto.ActorRequest;

import java.util.List;

public interface ActorsService {
    ActorRequest searchActorByDNI(String dni);

    ActorRequest updateActor(ActorRequest actorDTO);

    void deleteActor(Integer dni);

    ActorRequest searchActorById(Integer id);

    ActorRequest saveActor(ActorRequest actorDTO);

    List<ActorRequest> searchAllActors();
}
