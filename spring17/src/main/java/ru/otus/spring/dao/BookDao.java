package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import java.util.List;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
    List<Book> findAll();
}
