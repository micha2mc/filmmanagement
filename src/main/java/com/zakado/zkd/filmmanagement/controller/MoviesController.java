package com.zakado.zkd.filmmanagement.controller;

import com.zakado.zkd.filmmanagement.model.dto.MoviesRequest;
import com.zakado.zkd.filmmanagement.model.entity.Movie;
import com.zakado.zkd.filmmanagement.service.MoviesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Slf4j
public class MoviesController {

    private final MoviesService moviesService;

    @GetMapping
    public ResponseEntity<List<Movie>> searchAllMovies() {
        List<Movie> moviesDTOS = moviesService.searchAllMovies();
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Movie>> searchMovieByTitle(@PathVariable("title") final String title) {
        List<Movie> moviesDTOS = moviesService.searchMovieByTitle(title);
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }

    @GetMapping("/actor/{name}")
    public ResponseEntity<List<Movie>> searchMoviesByNameActor(@PathVariable("name") final String name) {
        List<Movie> moviesDTOS = moviesService.searchMoviesByNameActor(name);
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }

    /*@GetMapping("/genre/{genre}")
    public ResponseEntity<List<MoviesRequest>> searchMoviesByGenre(@PathVariable("genre") final String genre) {
        List<MoviesRequest> moviesDTOS = moviesService.searchMoviesByGenre(genre);
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }*/

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Movie>> searchMoviesByYear(@PathVariable("year") final Integer year) {
        List<Movie> moviesDTOS = moviesService.searchMoviesByYear(year);
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> searchMovieById(@PathVariable("id") final Integer id) {
        Movie moviesDTO = moviesService.searchMovieById(id);
        return new ResponseEntity<>(moviesDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody final Movie moviesRequest) {
        return new ResponseEntity<>(moviesService.saveMovie(moviesRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") final Integer id) {
        moviesService.deleteMovie(id);
    }

    @PutMapping
    public void updateMovie(@RequestBody final Movie moviesRequest) {
        moviesService.updateMovie(moviesRequest);
        log.info("updated movie with ID {}", moviesRequest.getNid());
    }
}
