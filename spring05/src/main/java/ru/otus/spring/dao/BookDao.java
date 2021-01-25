package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import java.util.List;

public interface BookDao {
    void insert(Book book);
    Book getById(Long id);
    List<Book> getAll();
    void update(Book book);
    void deleteById(Long id);
}
