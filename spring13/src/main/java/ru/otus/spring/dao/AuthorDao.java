package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.util.List;

@Repository
public interface AuthorDao extends JpaRepository<Author, Long> {
    List<Author> findAll();
}
