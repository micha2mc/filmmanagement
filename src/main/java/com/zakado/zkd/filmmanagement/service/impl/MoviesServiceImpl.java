package com.zakado.zkd.filmmanagement.service.impl;


import com.zakado.zkd.filmmanagement.dao.ActorsDAO;
import com.zakado.zkd.filmmanagement.dao.GenreDAO;
import com.zakado.zkd.filmmanagement.dao.MoviesDAO;
import com.zakado.zkd.filmmanagement.model.Actor;
import com.zakado.zkd.filmmanagement.model.Genre;
import com.zakado.zkd.filmmanagement.model.Movie;
import com.zakado.zkd.filmmanagement.service.MoviesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoviesServiceImpl implements MoviesService {

    private final MoviesDAO moviesDAO;
    private final ActorsDAO actorsDAO;
    private final GenreDAO genreDAO;

    @Override
    public List<Movie> searchAllMovies() {
        List<Movie> allMovies = moviesDAO.searchAllMovies();
        log.info("{} películas encontradas.", allMovies.size());
        return getListMoviesSorted(allMovies);
    }

    @Override
    public Movie saveMovie(Movie moviesRequest) {
        log.info("Añadiendo una nueva película {}", moviesRequest);
        Movie movie = moviesDAO.saveMovie(moviesRequest);
        setGenreAndActors(moviesRequest, movie);
        return moviesDAO.saveMovie(movie);
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

    @Override
    public List<Movie> searchMoviesByGenre(String genre) {
        List<Movie> allMovies = new ArrayList<>();
        List<Genre> listGenres = genreDAO.searchMoviesByGenre(genre);
        if (!listGenres.isEmpty()) {
            for (Genre genre1 : listGenres) {
                allMovies.addAll(genre1.getMoviesEntities());
            }
        }
        log.info("{} películas encontradas.", allMovies.size());
        return getListMoviesSorted(allMovies);
    }

    @Override
    public void deleteMovie(Integer idPeli) {
        Movie movie = moviesDAO.searchMovieById(idPeli);
        moviesDAO.deleteMovie(movie);
    }


    private static List<Movie> getListMoviesSorted(List<Movie> allMovies) {
        return allMovies.stream().sorted(Comparator.comparing(Movie::getYear).reversed()).toList();
    }

    private Movie getDataToUpdateMovie(Movie moviesRequest, Movie movie) {
        movie.setTitle(moviesRequest.getTitle());
        movie.setYear(moviesRequest.getYear());
        movie.setDuration(moviesRequest.getDuration());
        movie.setCountry(moviesRequest.getCountry());
        movie.setSynopsis(moviesRequest.getSynopsis());
        movie.setImage(Objects.nonNull(moviesRequest.getImage()) ? moviesRequest.getImage() : movie.getImage());
        movie.setYoutubeTrailerId(moviesRequest.getYoutubeTrailerId());
        setGenreAndActors(moviesRequest, movie);
        return movie;
    }

    private void setGenreAndActors(Movie moviesRequest, Movie movie) {
        if (Objects.nonNull(moviesRequest.getIdsGenero()) && !moviesRequest.getIdsGenero().isEmpty()) {
            for (Integer id : moviesRequest.getIdsGenero()) {
                Genre genre = genreDAO.searchGenreById(id);
                movie.addGenre(genre);
            }
        }
        if (Objects.nonNull(moviesRequest.getIdsActors()) && !moviesRequest.getIdsActors().isEmpty()) {
            for (Integer id : moviesRequest.getIdsActors()) {
                Actor actor = actorsDAO.searchActorById(id);
                movie.addActor(actor);
            }
        }
    }
}
