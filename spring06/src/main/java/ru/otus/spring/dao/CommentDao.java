package ru.otus.spring.dao;

import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    void insert(Comment comment);
    Optional<Comment> getById(long id);
    List<Comment> getAll();
    void update(Comment comment);
    void delete(Comment comment);
}
