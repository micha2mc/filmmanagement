package com.zakado.zkd.filmmanagement.dao.repository;

import com.zakado.zkd.filmmanagement.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActorsRepository extends JpaRepository<Actor, Integer> {

    List<Actor> findByNameContainingIgnoreCase(String name);
}
