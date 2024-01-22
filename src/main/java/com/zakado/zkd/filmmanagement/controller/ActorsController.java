package com.zakado.zkd.filmmanagement.controller;

import com.zakado.zkd.filmmanagement.model.entity.Actor;
import com.zakado.zkd.filmmanagement.service.ActorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actors")
public class ActorsController {

    private final ActorsService actorsService;

    @GetMapping
    public ResponseEntity<List<Actor>> searchAllActors() {
        List<Actor> actorsList = actorsService.searchAllActors();
        return new ResponseEntity<>(actorsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> searchActorById(@PathVariable("id") final Integer id) {
        return new ResponseEntity<>(actorsService.searchActorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Actor> saveActor(@RequestBody final Actor actorRequest) {
        return new ResponseEntity<>(actorsService.saveActor(actorRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable("id") final Integer id) {
        actorsService.deleteActor(id);
    }
}
