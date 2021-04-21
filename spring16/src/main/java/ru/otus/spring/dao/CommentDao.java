package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.domain.Comment;

@RepositoryRestResource(path = "COMMENTS")
public interface CommentDao extends JpaRepository<Comment, Long> {
}
