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
    public void insertBook(@ShellOption("id") String id, @ShellOption("name") String name, @ShellOption("author") String author, @ShellOption("genre") String genre) {
        bookService.insertBook(id, name, author, genre);
    }

    @ShellMethod(value = "Get book", key = {"gb", "getBook"})
    public void getBook(@ShellOption("id") String id) {
        bookService.getBook(id);
    }

    @ShellMethod(value = "List books", key = {"lb", "listBooks"})
    public void listBooks() {
        bookService.getBooks();
    }

    @ShellMethod(value = "Update book", key = {"ub", "updateBook"})
    public void updateBook(@ShellOption("id") String id, @ShellOption("name") String name, @ShellOption("author") String author, @ShellOption("genre") String genre) {
        bookService.updateBook(id, name, author, genre);
    }

    @ShellMethod(value = "Delete book", key = {"db", "deleteBook"})
    public void deleteBook(@ShellOption("id") String id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Insert comment", key = {"ic", "insertComment"})
    public void insertComment(@ShellOption("id") String id, @ShellOption("bookId") String bookId, @ShellOption("comment") String comment) {
        commentService.insertComment(id, bookId, comment);
    }

    @ShellMethod(value = "List comments", key = {"lc", "listComments"})
    public void listComments(@ShellOption("bookId") String bookId) {
        commentService.getComments(bookId);
    }

    @ShellMethod(value = "Update comment", key = {"uc", "updateComment"})
    public void updateComment(@ShellOption("id") String id, @ShellOption("comment") String comment) {
        commentService.updateComment(id, comment);
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "deleteComment"})
    public void deleteComment(@ShellOption("id") String id) {
        commentService.deleteComment(id);
    }
}
