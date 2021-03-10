package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookDao bookDao;

    @GetMapping("/books")
    public Flux<Book> getAll() {
        return bookDao.findAll();
    }

    @GetMapping("/books/{id}")
    public Mono<Book> get(@PathVariable String id) {
        return bookDao.findById(id);
    }

    @PostMapping("/books")
    public Mono<Book> save(@RequestBody Book book) {
        return bookDao.save(book);
    }

    @DeleteMapping("/books/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return bookDao.deleteById(id);
    }
}
