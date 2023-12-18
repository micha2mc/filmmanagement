package com.zakado.zkd.filmmanagement.controller;

import com.zakado.zkd.filmmanagement.model.dto.ActorRequest;
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
    public ResponseEntity<List<ActorRequest>> searchAllActors() {
        List<ActorRequest> actorsList = actorsService.searchAllActors();
        return new ResponseEntity<>(actorsList, HttpStatus.OK);
    }

    /*@GetMapping("/dni/{dni}")
    public ResponseEntity<ActorRequest> searchActorByDNI(@PathVariable("dni") final String dni) {
        return new ResponseEntity<>(actorsService.searchActorByDNI(dni), HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<ActorRequest> searchActorById(@PathVariable("id") final Integer id) {
        return new ResponseEntity<>(actorsService.searchActorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorRequest> saveActor(@RequestBody final ActorRequest actorRequest) {
        return new ResponseEntity<>(actorsService.saveActor(actorRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ActorRequest> updateActor(@RequestBody final ActorRequest actorDTO) {
        return new ResponseEntity<>(actorsService.updateActor(actorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable("id") final Integer id) {
        actorsService.deleteActor(id);
    }
}
