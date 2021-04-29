package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> findAllBookComments(Long bookId);
}
