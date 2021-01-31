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
    public void insertBook(String name, String authorName, Long genreId) {
        Genre genre = genreDao.findById(genreId).get();
        Author author = authorDao.findByName(authorName);
        bookDao.save(new Book(0, name, author, genre, null));
    }

    @Override
    public void getBook(Long id) {
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
    public void updateBook(Long id, String title, String authorName, Long genreId) {
        Book book = bookDao.findById(id).get();
        Author authorByName = authorDao.findByName(authorName);
        Genre genreById = genreDao.findById(genreId).get();

        book.setName(title);
        book.setAuthor(authorByName);
        book.setGenre(genreById);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        Book book = bookDao.findById(id).get();
        bookDao.delete(book);
    }
}
