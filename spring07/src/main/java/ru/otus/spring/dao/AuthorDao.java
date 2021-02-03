package ru.otus.spring.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Author;

public interface AuthorDao extends CrudRepository<Author, Long> {
    Author findByName(String name);
}
