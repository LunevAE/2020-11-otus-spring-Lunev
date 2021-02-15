package ru.otus.spring.service;

public interface CommentService {
    void insertComment(String id, String bookId, String comment);
    void getComments(String bookId);
    void updateComment(String id, String comment);
    void deleteComment(String id);
}
