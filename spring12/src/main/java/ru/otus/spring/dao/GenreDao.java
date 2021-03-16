package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Repository
public interface GenreDao extends JpaRepository<Genre, Long> {
    List<Genre> findAll();
}
