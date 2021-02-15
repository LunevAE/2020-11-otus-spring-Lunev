package ru.otus.spring.service;

public interface BookService {
    void insertBook(String id, String title, String authorId, String genreId);
    void updateBook(String id, String title, String authorId, String genreId);
    void deleteBook(String id);
    void getBooks();
    void getBook(String id);
}
