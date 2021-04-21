package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    void save(Book book);
    Optional<Book> findById(Long id);
    void delete(Long id);
}
