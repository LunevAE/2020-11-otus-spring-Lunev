package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;

@ShellComponent
public class ShellCommands {
    private final BookService bookService;
    private final CommentService commentService;

    public ShellCommands(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @ShellMethod(value = "Insert book", key = {"ib", "insertBook"})
    public void insertBook(@ShellOption("name") String name, @ShellOption("author") String authorName, @ShellOption("genre") Long genre) {
        bookService.insertBook(name, authorName, genre);
    }

    @ShellMethod(value = "Get book", key = {"gb", "getBook"})
    public void getBook(@ShellOption("id") Long id) {
        bookService.getBook(id);
    }

    @ShellMethod(value = "List books", key = {"lb", "listBooks"})
    public void listBooks() {
        bookService.getBooks();
    }

    @ShellMethod(value = "Update book", key = {"ub", "updateBook"})
    public void updateBook(@ShellOption("id") Long id, @ShellOption("name") String name, @ShellOption("author") String authorName, @ShellOption("genre") Long genre) {
        bookService.updateBook(id, name, authorName, genre);
    }

    @ShellMethod(value = "Delete book", key = {"db", "deleteBook"})
    public void deleteBook(@ShellOption("id") Long id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Insert comment", key = {"ic", "insertComment"})
    public void insertComment(@ShellOption("bookId") Long id, @ShellOption("comment") String comment) {
        commentService.insertComment(id, comment);
    }

    @ShellMethod(value = "List comments", key = {"lc", "listComments"})
    public void listComments(@ShellOption("bookId") Long bookId) {
        commentService.getComments(bookId);
    }

    @ShellMethod(value = "Update comment", key = {"uc", "updateComment"})
    public void updateComment(@ShellOption("id") Long id, @ShellOption("comment") String comment) {
        commentService.updateComment(id, comment);
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "deleteComment"})
    public void deleteComment(@ShellOption("id") Long id) {
        commentService.deleteComment(id);
    }
}
