package com.zakado.zkd.filmmanagement.dao;


import com.zakado.zkd.filmmanagement.model.Genre;

import java.util.List;

public interface GenreDAO {
    Genre saveGenre(Genre genre);

    List<Genre> getAllGenres();

    Genre searchGenreById(Integer id);

    List<Genre> searchMoviesByGenre(String genre);

    void deleteGenre(Integer id);
}
