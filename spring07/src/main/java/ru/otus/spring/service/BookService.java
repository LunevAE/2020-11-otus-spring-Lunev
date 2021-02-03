package ru.otus.spring.service;

public interface BookService {
    void insertBook(String title, String authorName, Long genreId);
    void updateBook(Long id, String title, String authorName, Long genreId);
    void deleteBook(Long id);
    void getBooks();
    void getBook(Long id);
}
