package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookDao {
    void insert(Book book);
    Optional<Book> getById(long id);
    List<Book> getAll();
    void update(Book book);
    void delete(Book book);
}
