package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Transactional
    @Override
    public void insertBook(String id, String name, String authorId, String genreId) {
        Genre genre = genreDao.findById(genreId).get();
        Author author = authorDao.findById(authorId).get();
        bookDao.save(new Book(id, name, author, genre));
    }

    @Override
    public void getBook(String id) {
        Book book = bookDao.findById(id).get();
        System.out.println(book.toString() + book.printAuthorAndGenre());
    }

    @Override
    public void getBooks(){
        List<Book> books = bookDao.findAll();
        for (Book book : books) {
            System.out.println(book.toString() + book.printAuthorAndGenre());
        }
    }

    @Transactional
    @Override
    public void updateBook(String id, String title, String authorId, String genreId) {
        Book book = bookDao.findById(id).get();
        Author authorByName = authorDao.findById(authorId).get();
        Genre genreById = genreDao.findById(genreId).get();

        book.setName(title);
        book.setAuthor(authorByName);
        book.setGenre(genreById);
        bookDao.save(book);
    }

    @Transactional
    @Override
    public void deleteBook(String id) {
        Book book = bookDao.findById(id).get();
        bookDao.delete(book);
    }
}
