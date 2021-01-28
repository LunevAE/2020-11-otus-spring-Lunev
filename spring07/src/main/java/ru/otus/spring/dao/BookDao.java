package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Book;
import java.util.List;

public interface BookDao extends CrudRepository<Book, Long>{
    List<Book> findAll();
}
