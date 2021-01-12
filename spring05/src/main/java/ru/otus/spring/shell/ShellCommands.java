package ru.otus.spring.shell;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

@ShellComponent
public class ShellCommands {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public ShellCommands(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "Insert book", key = {"i", "insertBook"})
    public void insertBook(@ShellOption("id") Long id, @ShellOption("name") String name, @ShellOption("author") Long author, @ShellOption("genre") Long genre) {
        Genre genreDaoById = genreDao.getById(genre);
        Author authorDaoById = authorDao.getById(author);
        try {
            bookDao.insert(new Book(id, name, authorDaoById, genreDaoById));
        } catch (DuplicateKeyException e) {
            System.out.println("Book already exists");
        }
    }

    @ShellMethod(value = "Get book", key = {"g", "getBook"})
    public void getBook(@ShellOption("id") Long id) {
        Book book = bookDao.getById(id);
        System.out.println(book.toString());
    }

    @ShellMethod(value = "List books", key = {"l", "listBooks"})
    public void listBooks() {
        List<Book> books = bookDao.getAll();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @ShellMethod(value = "Update book", key = {"u", "updateBook"})
    public void updateBook(@ShellOption("id") Long id, @ShellOption("name") String name, @ShellOption("author") Long author, @ShellOption("genre") Long genre) {
        Genre genreDaoById = genreDao.getById(genre);
        Author authorDaoById = authorDao.getById(author);
        bookDao.update(new Book(id, name, authorDaoById, genreDaoById));
    }

    @ShellMethod(value = "Delete book", key = {"d", "deleteBook"})
    public void deleteBook(@ShellOption("id") Long id) {
        bookDao.deleteById(id);
    }

}
