package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> get(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }
}
