package com.zakado.zkd.filmmanagement.service;

import com.zakado.zkd.filmmanagement.model.entity.Genre;

import java.util.List;

public interface GenreService {
    Genre saveGenre(Genre genre);

    List<Genre> getAllGenres();
}
