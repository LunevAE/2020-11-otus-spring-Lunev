package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class RouteTest {

    @Autowired
    private RouterFunction route;

    @MockBean
    private BookDao bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private WebTestClient client;
    private Book book;

    public static final String TEST_BOOK_NAME = "Test Book String";

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToRouterFunction(route).build();
        var genre = new Genre("2", "genre");
        var author = new Author("2", "author");
        book = new Book("2", TEST_BOOK_NAME, author, genre);
    }

    @SneakyThrows
    @Test
    public void shouldSaveBook() {
        given(bookRepository.save(any())).willReturn(Mono.just(book));

        client.post()
                .uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(book))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .json(objectMapper.writeValueAsString(book));
    }

    @SneakyThrows
    @Test
    public void shouldReturnAllBooks() {
        var books = List.of(book);

        given(bookRepository.findAll()).willReturn(Flux.just(book));

        client.get()
                .uri("/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(objectMapper.writeValueAsString(books));
    }

    @SneakyThrows
    @Test
    void shouldReturnBookById() {
        given(bookRepository.findById("2")).willReturn(Mono.just(book));

        client.get()
                .uri("/books/2")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(objectMapper.writeValueAsString(book));
    }


    @SneakyThrows
    @Test
    public void shouldDeleteBook() {
        given(bookRepository.deleteById("2")).willReturn(Mono.empty());

        client.delete()
                .uri("/books/2")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .isEmpty();
    }
}