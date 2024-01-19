package com.zakado.zkd.filmmanagement.dao;

import com.zakado.zkd.filmmanagement.model.entity.Movie;

import java.util.List;
import java.util.Set;

public interface MoviesDAO {
    List<Movie> searchAllMovies();
    Movie saveMovie(Movie movie);
    Movie searchMovieById(Integer id);

    List<Movie> searchMovieByTitle(String strTitle);

    List<Movie> searchMoviesByYear(Integer year);

    void deleteMovie(Movie movie);

}
