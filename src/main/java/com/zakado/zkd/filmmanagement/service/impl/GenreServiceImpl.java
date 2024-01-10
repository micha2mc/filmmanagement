package com.zakado.zkd.filmmanagement.service.impl;

import com.zakado.zkd.filmmanagement.dao.GenreDAO;
import com.zakado.zkd.filmmanagement.model.dto.ActorRequest;
import com.zakado.zkd.filmmanagement.model.entity.Genre;
import com.zakado.zkd.filmmanagement.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDAO genreDAO;
    @Override
    public Genre saveGenre(Genre genre) {
        return genreDAO.saveGenre(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> allGenres = genreDAO.getAllGenres();
        return allGenres.stream().sorted(Comparator.comparing(Genre::getDescription)).toList();
    }

    @Override
    public Genre searchGenreById(Integer id) {
        return genreDAO.searchGenreById(id);
    }
}
