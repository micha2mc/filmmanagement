package com.zakado.zkd.filmmanagement.service.impl;

import com.zakado.zkd.filmmanagement.dao.ActorsDAO;
import com.zakado.zkd.filmmanagement.model.dto.ActorRequest;
import com.zakado.zkd.filmmanagement.model.entity.Actor;
import com.zakado.zkd.filmmanagement.service.ActorsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorsServiceImpl implements ActorsService {

    private final ActorsDAO actorsDAO;

    /*@Override
    public ActorRequest searchActorByDNI(String dni) {
        Actor actor = actorsDAO.searchActorByDNI(dni).orElse(null);
        assert actor != null;
        return FilmManagementUtils.entityToActorRequest(actor);
    }*/

    @Override
    public ActorRequest updateActor(ActorRequest actor) {
        return actor;
        /*Optional<Actor> actorsTemp = actorsDAO.searchActorByDNI(actor.getDni());
        if (actorsTemp.isPresent()) {
            Actor actor1 = actorsDAO.updateActor(actorsTemp.get());
            return FilmManagementUtils.entityToActorRequest(actor1);
        } else {
            throw new RuntimeException("No existe actor para actualizar");
        }*/
    }

    @Override
    public void deleteActor(Integer id) {
        Actor actor = actorsDAO.searchActorById(id).orElse(null);
        actorsDAO.deleteActor(actor);
    }

    @Override
    public Actor searchActorById(Integer id) {
        return actorsDAO.searchActorById(id).orElse(null);
    }

    @Override
    public Actor saveActor(Actor actorRequest) {
        return actorsDAO.saveActor(actorRequest);
    }

    @Override
    public List<Actor> searchAllActors() {
        return actorsDAO.searchAllActors().stream()
                .sorted(Comparator.comparing(Actor::getName)).toList();
    }
}
