package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import java.util.List;

@Repository
public interface AuthorDao extends CrudRepository<Author, Long> {
    List<Author> findAll();
}
