package com.zakado.zkd.filmmanagement.service;


import com.zakado.zkd.filmmanagement.model.Movie;

import java.util.List;

public interface MoviesService {
    List<Movie> searchAllMovies();

    Movie saveMovie(Movie moviesRequest);

    void deleteMovie(Integer idPeli);

    void updateMovie(Movie moviesRequest);

    Movie searchMovieById(Integer id);

    List<Movie> searchMovieByTitle(String title);

    List<Movie> searchMoviesByNameActor(String name);

    List<Movie> searchMoviesByYear(Integer year);


    List<Movie> searchMoviesByGenre(String genre);
}
