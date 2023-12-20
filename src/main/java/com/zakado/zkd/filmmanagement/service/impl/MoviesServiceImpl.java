package com.zakado.zkd.filmmanagement.service.impl;

import com.zakado.zkd.filmmanagement.dao.ActorsDAO;
import com.zakado.zkd.filmmanagement.dao.MoviesDAO;
import com.zakado.zkd.filmmanagement.model.dto.MoviesRequest;
import com.zakado.zkd.filmmanagement.model.entity.Actor;
import com.zakado.zkd.filmmanagement.model.entity.Genre;
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
    public List<Movie> searchAllMovies() {
        List<Movie> allMovies = moviesDAO.searchAllMovies();
        log.info("{} películas encontradas.", allMovies.size());
        return getListMoviesSorted(allMovies);
    }

    @Override
    public Movie saveMovie(Movie moviesRequest) {
        log.info("Añadiendo una nueva película {}", moviesRequest);
        return moviesDAO.saveMovie(moviesRequest);
    }

    @Override
    public void updateMovie(final Movie moviesRequest) {

        Movie movie = moviesDAO.searchMovieById(moviesRequest.getNid());
        if (Objects.nonNull(movie)) {
            Movie moviesUpdated = moviesDAO.saveMovie(getDataToUpdateMovie(moviesRequest, movie));
            log.info("Actualizada la película con ID {}", moviesUpdated.getNid());
        } else {
            throw new RuntimeException("No existe pelicula para actualizar");
        }
    }

    private Movie getDataToUpdateMovie(Movie moviesRequest, Movie movie) {
        movie.setTitle(moviesRequest.getTitle());
        movie.setYear(moviesRequest.getYear());
        movie.setDuration(moviesRequest.getDuration());
        movie.setCountry(moviesRequest.getCountry());
        movie.setSynopsis(moviesRequest.getSynopsis());
        movie.setImage(moviesRequest.getImage());
        return movie;
    }

    @Override
    public Movie searchMovieById(Integer id) {
        return moviesDAO.searchMovieById(id);
    }

    @Override
    public List<Movie> searchMovieByTitle(String title) {
        List<Movie> moviesEntities = moviesDAO.searchMovieByTitle(title);
        log.info("{} películas encontradas.", moviesEntities.size());
        return getListMoviesSorted(moviesEntities);
    }

    @Override
    public List<Movie> searchMoviesByNameActor(String name) {
        List<Movie> allMovies = new ArrayList<>();
        List<Actor> actorsEntities = actorsDAO.searchMoviesByNameActor(name);
        if (!actorsEntities.isEmpty()) {
            for (Actor actor : actorsEntities) {
                allMovies.addAll(actor.getMoviesEntities());
            }
        }
        return getListMoviesSorted(allMovies);
    }

    @Override
    public List<Movie> searchMoviesByYear(Integer year) {
        List<Movie> moviesEntities = moviesDAO.searchMoviesByYear(year);
        return getListMoviesSorted(moviesEntities);
    }

    /*@Override
    public List<MoviesRequest> searchMoviesByGenre(String genre) {
        Set<Movie> moviesEntities = moviesDAO.searchMoviesByGenre(genre);
        log.info("{} películas encontradas.", moviesEntities.size());
        return getListMoviesSorted(moviesEntities);
    }*/

    @Override
    public void deleteMovie(Integer idPeli) {
        Movie movie = moviesDAO.searchMovieById(idPeli);
        moviesDAO.deleteMovie(movie);
    }


    private static List<Movie> getListMoviesSorted(List<Movie> allMovies) {
        return allMovies.stream().sorted(Comparator.comparing(Movie::getYear).reversed()).toList();
    }

    private boolean isNotPresent(Set<Movie> movieByTitle, String title) {

        return movieByTitle.stream().anyMatch(pel -> FilmManagementUtils.removeSpace(pel.getTitle())
                .equalsIgnoreCase(FilmManagementUtils.removeSpace(title)));
    }
}
