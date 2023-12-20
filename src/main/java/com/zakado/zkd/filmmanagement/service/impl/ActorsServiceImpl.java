package com.zakado.zkd.filmmanagement.service.impl;

import com.zakado.zkd.filmmanagement.dao.ActorsDAO;
import com.zakado.zkd.filmmanagement.model.dto.ActorRequest;
import com.zakado.zkd.filmmanagement.model.entity.Actor;
import com.zakado.zkd.filmmanagement.model.entity.Movie;
import com.zakado.zkd.filmmanagement.service.ActorsService;
import com.zakado.zkd.filmmanagement.utils.FilmManagementUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

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
        if (Objects.nonNull(actor)) {
            Set<Movie> moviesEntities = actor.getMoviesEntities();
            for (Movie movies : moviesEntities) {
                movies.removeActor(actor);
            }
            actorsDAO.saveActor(actor);
            log.info("Actor con id {} eliminado.", actor.getNid());
        }
        log.info("Actor con id {} no existe.", id);
    }

    @Override
    public ActorRequest searchActorById(Integer id) {
        Actor actor = actorsDAO.searchActorById(id).orElse(null);
        return FilmManagementUtils.entityToActorRequest(actor);
    }

    @Override
    public Actor saveActor(Actor actorRequest) {
        return actorsDAO.saveActor(actorRequest);
    }

    @Override
    public List<Actor> searchAllActors() {
        return actorsDAO.searchAllActors();
        /*return listActors.stream().map(FilmManagementUtils::entityToActorRequest)
                .sorted(Comparator.comparing(ActorRequest::getName)).toList();*/
    }
}
