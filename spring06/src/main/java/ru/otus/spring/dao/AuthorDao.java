package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void insert(Author author);
    Optional<Author> getById(long id);
    List<Author> getAll();
    void update(Author author);
    void delete(Author author);
}
