package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@WithMockUser(
        username = "user",
        roles = {"user"}
)
class BookControllerTest {
    private static final Long BOOK_ID = 2L;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @MockBean
    private BookService bookService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private CommentService commentService;


    @SneakyThrows
    @Test
    void getList() {
        Author author = new Author(1L, "Harper Lee");
        Genre genre = new Genre(1L, "novel");
        List<Book> expectedBooks = List.of(
                new Book(2L, "new book", author, genre));

        given(bookService.findAll()).willReturn(expectedBooks);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", equalTo(expectedBooks)));
    }

    @SneakyThrows
    @Test
    void view() {
        Author author = new Author(1L, "Harper Lee");
        Genre genre = new Genre(1L, "novel");
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book", author, genre, new ArrayList<>()));

        given(bookService.findById(anyLong())).willReturn(expectedBook);

        mvc.perform(get("/books/book/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", equalTo(expectedBook.get())));
    }

    @SneakyThrows
    @Test
    void editPage() {
        Author author = new Author(1L, "Harper Lee");
        Genre genre = new Genre(1L, "novel");
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book", author, genre));

        given(bookService.findById(anyLong())).willReturn(expectedBook);
        given(genreService.findAll()).willReturn(List.of(expectedBook.get().getGenre()));
        given(authorService.findAll()).willReturn(List.of(expectedBook.get().getAuthor()));

        mvc.perform(get("/books/edit/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", equalTo(expectedBook.get())))
                .andExpect(model().attribute("genres", equalTo(List.of(expectedBook.get().getGenre()))))
                .andExpect(model().attribute("authors", equalTo(List.of(expectedBook.get().getAuthor()))));
    }

    @SneakyThrows
    @Test
    void create() {
        Author author = new Author(1L, "Harper Lee");
        Genre genre = new Genre(1L, "novel");
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book", author, genre));

        mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(expectedBook.get())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @SneakyThrows
    @Test
    void delete() {
        mvc.perform(MockMvcRequestBuilders.delete("/books/" + BOOK_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

}
