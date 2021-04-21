package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring.domain.Book;

import java.util.Optional;

@RepositoryRestResource(path = "BOOKS")
public interface BookDao extends JpaRepository<Book, Long> {
    @RestResource(path = "titles", rel = "title")
    Optional<Book> findByTitle(String title);
}
