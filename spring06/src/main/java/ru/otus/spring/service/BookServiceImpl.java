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
    public void insertBook(String name, Long authorId, Long genreId) {
        Genre genre = genreDao.getById(genreId).get();
        Author author = authorDao.getById(authorId).get();
        bookDao.insert(new Book(0, name, author, genre, null));
    }

    @Override
    public void getBook(Long id) {
        Book book = bookDao.getById(id).get();
        System.out.println(book.toString());
    }

    @Override
    public void getBooks(){
        List<Book> books = bookDao.getAll();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @Transactional
    @Override
    public void updateBook(Long id, String title, Long author, Long genre) {
        Book book = bookDao.getById(id).get();
        Author authorDaoById = authorDao.getById(author).get();
        Genre genreDaoById = genreDao.getById(genre).get();

        book.setName(title);
        book.setAuthor(authorDaoById);
        book.setGenre(genreDaoById);

        bookDao.update(book);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        Book book = bookDao.getById(id).get();
        bookDao.delete(book);
    }
}
