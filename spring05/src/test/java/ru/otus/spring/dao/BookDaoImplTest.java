package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
class BookDaoImplTest {
    private static final int EXPECTED_BOOK_COUNT = 3;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;

    @Test
    void shouldInsertBook() {
        Genre genre = genreDao.getById(1L);
        assertThat(genre).isNotNull();
        Author author = authorDao.getById(2L);
        assertThat(author).isNotNull();

        Book expectedBook = new Book(4, "aaa", author, genre);
        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldGetBookById() {
        Genre genre = genreDao.getById(1L);
        assertThat(genre).isNotNull();
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();

        Book expectedBook = new Book(1, "To Kill a Mockingbird", author, genre);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldReturnExpectedBooksCount() {
        int actualBooksCount = bookDao.getAll().size();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @Test
    void shouldUpdateBook() {
        Book expectedBook = bookDao.getById(1L);
        expectedBook.setName("aaa");

        bookDao.update(expectedBook);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldDeleteBookById() {
        long bookId = 1L;
        bookDao.deleteById(bookId);
        assertThatThrownBy(() -> bookDao.getById(bookId)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}
