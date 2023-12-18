package com.zakado.zkd.filmmanagement.controller;

import com.zakado.zkd.filmmanagement.model.dto.MoviesRequest;
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
    public ResponseEntity<List<MoviesRequest>> searchAllMovies() {
        List<MoviesRequest> moviesDTOS = moviesService.searchAllMovies();
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<MoviesRequest>> searchMovieByTitle(@PathVariable("title") final String title) {
        List<MoviesRequest> moviesDTOS = moviesService.searchMovieByTitle(title);
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }

    @GetMapping("/actor/{name}")
    public ResponseEntity<List<MoviesRequest>> searchMoviesByNameActor(@PathVariable("name") final String name) {
        List<MoviesRequest> moviesDTOS = moviesService.searchMoviesByNameActor(name);
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }

    /*@GetMapping("/genre/{genre}")
    public ResponseEntity<List<MoviesRequest>> searchMoviesByGenre(@PathVariable("genre") final String genre) {
        List<MoviesRequest> moviesDTOS = moviesService.searchMoviesByGenre(genre);
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }*/

    @GetMapping("/year/{year}")
    public ResponseEntity<List<MoviesRequest>> searchMoviesByYear(@PathVariable("year") final Integer year) {
        List<MoviesRequest> moviesDTOS = moviesService.searchMoviesByYear(year);
        return new ResponseEntity<>(moviesDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoviesRequest> searchMovieById(@PathVariable("id") final Integer id) {
        MoviesRequest moviesDTO = moviesService.searchMovieById(id);
        return new ResponseEntity<>(moviesDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MoviesRequest> saveMovie(@RequestBody final MoviesRequest moviesRequest) {
        return new ResponseEntity<>(moviesService.saveMovie(moviesRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") final Integer id) {
        moviesService.deleteMovie(id);
    }

    @PutMapping
    public void updateMovie(@RequestBody final MoviesRequest moviesRequest) {
        moviesService.updateMovie(moviesRequest);
        log.info("updated movie with ID {}", moviesRequest.getNid());
    }
}
