package com.zakado.zkd.filmmanagement.service.impl;

import com.zakado.zkd.filmmanagement.dao.ActorsDAO;
import com.zakado.zkd.filmmanagement.model.entity.Actor;
import com.zakado.zkd.filmmanagement.model.entity.Movie;
import com.zakado.zkd.filmmanagement.service.ActorsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorsServiceImpl implements ActorsService {

    private final ActorsDAO actorsDAO;


    @Override
    public void deleteActor(Integer id) {
        Actor actor = actorsDAO.searchActorById(id);
        if (Objects.nonNull(actor)) {
            for (Movie movie : actor.getMoviesEntities()) {
                movie.removeActor(actor);
            }
        }
        actorsDAO.deleteActor(actor);
    }

    @Override
    public Actor searchActorById(Integer id) {
        return actorsDAO.searchActorById(id);
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
