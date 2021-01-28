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
import java.util.Optional;

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
        Genre genre = genreDao.findById(genreId).get();
        Author author = authorDao.findById(authorId).get();
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
    public void updateBook(Long id, String title, Long author, Long genre) {
        Book book = bookDao.findById(id).get();
        Author authorDaoById = authorDao.findById(author).get();
        Genre genreDaoById = genreDao.findById(genre).get();

        book.setName(title);
        book.setAuthor(authorDaoById);
        book.setGenre(genreDaoById);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        Optional<Book> book = bookDao.findById(id);
        bookDao.delete(book.get());
    }
}
