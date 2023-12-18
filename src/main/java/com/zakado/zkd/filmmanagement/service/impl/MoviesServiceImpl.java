package com.zakado.zkd.filmmanagement.service.impl;

import com.zakado.zkd.filmmanagement.dao.ActorsDAO;
import com.zakado.zkd.filmmanagement.dao.MoviesDAO;
import com.zakado.zkd.filmmanagement.model.dto.ActorRequest;
import com.zakado.zkd.filmmanagement.model.dto.MoviesRequest;
import com.zakado.zkd.filmmanagement.model.entity.Actor;
import com.zakado.zkd.filmmanagement.model.entity.Movie;
import com.zakado.zkd.filmmanagement.service.MoviesService;
import com.zakado.zkd.filmmanagement.utils.FilmManagementUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoviesServiceImpl implements MoviesService {

    private final MoviesDAO moviesDAO;
    private final ActorsDAO actorsDAO;

    @Override
    public List<MoviesRequest> searchAllMovies() {
        Set<Movie> allMovies = moviesDAO.searchAllMovies();
        log.info("{} películas encontradas.", allMovies.size());
        return getListMoviesSorted(allMovies);
    }

    @Override
    public MoviesRequest saveMovie(MoviesRequest moviesRequest) {
        log.info("Añadiendo una nueva película {}", moviesRequest);

        if (Objects.isNull(moviesRequest.getNid())) {
            String strTitle = moviesRequest.getTitle().trim();
            Set<Movie> movieByTitle = moviesDAO.searchMovieByTitle(strTitle);
            if (movieByTitle.isEmpty()) {
                Movie movie = moviesDAO.saveMovie(FilmManagementUtils.movieRequestToEntity(moviesRequest));
                moviesRequest = FilmManagementUtils.entityToMovieRequest(movie);
            } else if (isNotPresent(movieByTitle, strTitle)) {
                Movie movieTemp = movieByTitle.stream()
                        .filter(pel -> FilmManagementUtils.removeSpace(pel.getTitle())
                                .equalsIgnoreCase(FilmManagementUtils.removeSpace(strTitle))
                                && pel.getStatus().equalsIgnoreCase("N")).findFirst().orElse(null);

                assert movieTemp != null;
                movieTemp.setStatus("A");
                moviesRequest = FilmManagementUtils.entityToMovieRequest(moviesDAO.saveMovie(movieTemp));
            } else {
                Movie movie = moviesDAO.saveMovie(FilmManagementUtils.movieRequestToEntity(moviesRequest));
                moviesRequest = FilmManagementUtils.entityToMovieRequest(movie);
            }
            return moviesRequest;
        } else {
            Set<ActorRequest> collectActors = moviesRequest.getActors();

            if (Objects.nonNull(collectActors) && !collectActors.isEmpty()) {
                Movie movie = moviesDAO.searchMovieById(moviesRequest.getNid());
                List<Integer> idsActor = new ArrayList<>();
                for (Actor actor : movie.getActors()) {
                    idsActor.add(actor.getNid());
                }
                for (ActorRequest actorRequest : collectActors) {
                    if (!idsActor.contains(actorRequest.getNid())) {
                        movie.addActor(FilmManagementUtils.actorRequestToEntity(actorRequest));
                    }
                }
                return FilmManagementUtils.entityToMovieRequest(moviesDAO.saveMovie(movie));
            }
        }
        return moviesRequest;
    }

    @Override
    public void updateMovie(final MoviesRequest moviesRequest) {

        Movie movie = moviesDAO.searchMovieById(moviesRequest.getNid());
        if (Objects.nonNull(movie)) {
            Movie moviesUpdated = moviesDAO.saveMovie(getDataToUpdateMovie(moviesRequest, movie));
            log.info("Actualizada la película con ID {}", moviesUpdated.getNid());
        } else {
            throw new RuntimeException("No existe pelicula para actualizar");
        }
    }

    private Movie getDataToUpdateMovie(MoviesRequest moviesRequest, Movie movie) {
        movie.setTitle(moviesRequest.getTitle());
        movie.setYear(moviesRequest.getYear());
        movie.setDuration(moviesRequest.getDuration());
        movie.setCountry(moviesRequest.getCountry());
        movie.setGenre(moviesRequest.getGenre());
        movie.setSynopsis(moviesRequest.getSynopsis());
        movie.setImage(moviesRequest.getImage());
        return movie;
    }

    @Override
    public MoviesRequest searchMovieById(Integer id) {
        return FilmManagementUtils.entityToMovieRequest(moviesDAO.searchMovieById(id));
    }

    @Override
    public List<MoviesRequest> searchMovieByTitle(String title) {
        Set<Movie> moviesEntities = moviesDAO.searchMovieByTitle(title);
        log.info("{} películas encontradas.", moviesEntities.size());
        return getListMoviesSorted(moviesEntities);
    }

    @Override
    public List<MoviesRequest> searchMoviesByNameActor(String name) {
        Set<Movie> allMovies = new HashSet<>();
        List<Actor> actorsEntities = actorsDAO.searchMoviesByNameActor(name);
        if (!actorsEntities.isEmpty()) {
            for (Actor actor : actorsEntities) {
                for (Movie movie : actor.getMoviesEntities()) {
                    if ("A".equalsIgnoreCase(movie.getStatus())) {
                        allMovies.add(movie);
                    }

                }
            }
        }
        return getListMoviesSorted(allMovies);
    }

    @Override
    public List<MoviesRequest> searchMoviesByYear(Integer year) {
        Set<Movie> moviesEntities = moviesDAO.searchMoviesByYear(year);
        return getListMoviesSorted(moviesEntities);
    }

    @Override
    public List<MoviesRequest> searchMoviesByGenre(String genre) {
        Set<Movie> moviesEntities = moviesDAO.searchMoviesByGenre(genre);
        log.info("{} películas encontradas.", moviesEntities.size());
        return getListMoviesSorted(moviesEntities);
    }

    @Override
    public void deleteMovie(Integer idPeli) {
        Movie movie = moviesDAO.searchMovieById(idPeli);
        if (Objects.nonNull(movie)) {
            movie.setStatus("N");
            moviesDAO.saveMovie(movie);
            log.info("Película eliminada con éxito");
        } else {
            log.info("Película con ID " + idPeli + " no existe");
        }
    }


    private static List<MoviesRequest> getListMoviesSorted(Set<Movie> allMovies) {
        return allMovies.stream().map(FilmManagementUtils::entityToMovieRequest)
                .sorted(Comparator.comparing(MoviesRequest::getYear).reversed()).toList();
    }

    private boolean isNotPresent(Set<Movie> movieByTitle, String title) {

        return movieByTitle.stream().anyMatch(pel -> FilmManagementUtils.removeSpace(pel.getTitle())
                .equalsIgnoreCase(FilmManagementUtils.removeSpace(title))
                && pel.getStatus().equalsIgnoreCase("N"));
    }
}
