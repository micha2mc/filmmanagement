package com.zakado.zkd.filmmanagement.dao.impl;

import com.zakado.zkd.filmmanagement.dao.MoviesDAO;
import com.zakado.zkd.filmmanagement.dao.repository.MoviesRepository;
import com.zakado.zkd.filmmanagement.model.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class MoviesDAOImpl implements MoviesDAO {
    private final MoviesRepository moviesRepository;

    @Override
    public List<Movie> searchAllMovies() {
        return moviesRepository.findAll();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return moviesRepository.save(movie);
    }


    @Override
    public Movie searchMovieById(Integer id) {
        return moviesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> searchMovieByTitle(String strTitle) {
        return moviesRepository
                .findByTitleContainingIgnoreCase(strTitle);
    }

    @Override
    public List<Movie> searchMoviesByYear(Integer year) {
        return moviesRepository.findByYear(year);
    }

    @Override
    public void deleteMovie(Movie movie) {
        moviesRepository.delete(movie);
    }



}
