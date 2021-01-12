package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import java.util.List;

public interface AuthorDao {
    void insert(Author author);
    Author getById(Long id);
    List<Author> getAll();
    void update(Author author);
    void deleteById(Long id);
}
