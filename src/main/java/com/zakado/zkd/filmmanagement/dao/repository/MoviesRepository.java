package com.zakado.zkd.filmmanagement.dao.repository;

import com.zakado.zkd.filmmanagement.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MoviesRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    //Set<Movie> findByStatus(String status);

    List<Movie> findByYear(Integer year);

    //Set<Movie> findByGenre(String genre);
}
