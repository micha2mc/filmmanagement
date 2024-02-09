package com.zakado.zkd.filmmanagement.dao.repository;


import com.zakado.zkd.filmmanagement.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByYear(Integer year);
}
