package com.zakado.zkd.filmmanagement.dao.impl;

import com.zakado.zkd.filmmanagement.dao.GenreDAO;
import com.zakado.zkd.filmmanagement.dao.repository.GenreRepository;
import com.zakado.zkd.filmmanagement.model.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDAOImpl implements GenreDAO {
    private final GenreRepository genreRepository;

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
