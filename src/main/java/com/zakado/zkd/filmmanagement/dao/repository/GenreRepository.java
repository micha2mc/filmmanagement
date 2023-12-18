package com.zakado.zkd.filmmanagement.dao.repository;

import com.zakado.zkd.filmmanagement.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
