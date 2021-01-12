package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;
import java.util.List;

public interface GenreDao {
    void insert(Genre genre);
    Genre getById(Long id);
    List<Genre> getAll();
    void update(Genre genre);
    void deleteById(Long id);
}
