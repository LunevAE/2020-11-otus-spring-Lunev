package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreDao {
    void insert(Genre genre);
    Optional<Genre> getById(long id);
    List<Genre> getAll();
    void update(Genre genre);
    void delete(Genre genre);
}
