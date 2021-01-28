package ru.otus.spring.service;

public interface CommentService {
    void insertComment(Long bookId, String comment);
    void getComments(Long bookId);
    void updateComment(Long id, String comment);
    void deleteComment(Long id);
}
