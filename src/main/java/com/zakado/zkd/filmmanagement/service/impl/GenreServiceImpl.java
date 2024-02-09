package com.zakado.zkd.filmmanagement.service.impl;


import com.zakado.zkd.filmmanagement.dao.GenreDAO;
import com.zakado.zkd.filmmanagement.model.Genre;
import com.zakado.zkd.filmmanagement.model.Movie;
import com.zakado.zkd.filmmanagement.service.GenreService;
import com.zakado.zkd.filmmanagement.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDAO genreDAO;
    private final MoviesService moviesService;

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

    @Override
    public void deleteGenre(Integer id) {
        Genre genre = genreDAO.searchGenreById(id);
        if (Objects.nonNull(genre)) {
            List<Movie> movies = moviesService.searchMoviesByGenre(genre.getDescription());
            for (Movie movie : movies) {
                movie.removeGenre(genre);
            }
        }
        genreDAO.deleteGenre(id);
    }
}
