package com.zakado.zkd.filmmanagement.dao;

import com.zakado.zkd.filmmanagement.model.entity.Movie;

import java.util.Set;

public interface MoviesDAO {
    Set<Movie> searchAllMovies();
    Movie saveMovie(Movie movie);
    Movie searchMovieById(Integer id);

    Set<Movie> searchMovieByTitle(String strTitle);

    Set<Movie> searchMoviesByYear(Integer year);

}
