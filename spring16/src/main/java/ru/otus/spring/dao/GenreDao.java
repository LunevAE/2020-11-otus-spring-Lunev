package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.domain.Genre;

@RepositoryRestResource(path = "GENRES")
public interface GenreDao extends JpaRepository<Genre, Long> {
}
