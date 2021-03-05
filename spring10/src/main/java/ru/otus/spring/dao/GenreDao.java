package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Repository
public interface GenreDao extends CrudRepository<Genre, Long> {
    List<Genre> findAll();
}
