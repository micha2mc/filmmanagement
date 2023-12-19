package com.zakado.zkd.filmmanagement.dao;

import com.zakado.zkd.filmmanagement.model.entity.Genre;

import java.util.List;

public interface GenreDAO {
    Genre saveGenre(Genre genre);

    List<Genre> getAllGenres();
}
