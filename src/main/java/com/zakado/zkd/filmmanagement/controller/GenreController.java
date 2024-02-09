package com.zakado.zkd.filmmanagement.controller;


import com.zakado.zkd.filmmanagement.model.Genre;
import com.zakado.zkd.filmmanagement.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genreList = genreService.getAllGenres();
        return new ResponseEntity<>(genreList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> searchActorById(@PathVariable("id") final Integer id) {
        return new ResponseEntity<>(genreService.searchGenreById(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Genre> saveGenre(@RequestBody final Genre genre) {
        return new ResponseEntity<>(genreService.saveGenre(genre), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") final Integer id) {
        genreService.deleteGenre(id);
    }
}
