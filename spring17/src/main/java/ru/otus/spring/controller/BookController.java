package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentService commentService;

    @GetMapping()
    public String sayHello() {
        return "index";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "listBooks";
    }

    @GetMapping("/books/newBook")
    public String newBook(Book book, Model model) {
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "newBook";
    }

    @PostMapping("/books")
    public String create(Book book, Model model) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/book/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).get());
        model.addAttribute("comments", commentService.findAllBookComments(id));
        return "showBook";
    }

    @GetMapping("/books/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).get());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "editBook";
    }

    @DeleteMapping("/books/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
