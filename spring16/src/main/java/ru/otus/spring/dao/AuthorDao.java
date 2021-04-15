package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.domain.Author;

@RepositoryRestResource(path = "AUTHORS")
public interface AuthorDao extends JpaRepository<Author, Long> {
}
