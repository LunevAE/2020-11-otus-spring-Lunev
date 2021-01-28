package ru.otus.spring.service;

public interface BookService {
    void insertBook(String title, Long genre, Long author);
    void updateBook(Long id, String title, Long genre, Long author);
    void deleteBook(Long id);
    void getBooks();
    void getBook(Long id);
}
