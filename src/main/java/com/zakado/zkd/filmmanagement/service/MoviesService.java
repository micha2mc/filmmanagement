package com.zakado.zkd.filmmanagement.service;

import com.zakado.zkd.filmmanagement.model.dto.MoviesRequest;

import java.util.List;

public interface MoviesService {
    List<MoviesRequest> searchAllMovies();

    MoviesRequest saveMovie(MoviesRequest moviesRequest);

    void deleteMovie(Integer idPeli);

    void updateMovie(MoviesRequest moviesRequest);

    MoviesRequest searchMovieById(Integer id);

    List<MoviesRequest> searchMovieByTitle(String title);

    List<MoviesRequest> searchMoviesByNameActor(String name);

    List<MoviesRequest> searchMoviesByYear(Integer year);

    List<MoviesRequest> searchMoviesByGenre(String genre);
}
