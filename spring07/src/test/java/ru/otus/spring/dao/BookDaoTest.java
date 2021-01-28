package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import javax.persistence.NoResultException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(connection= EmbeddedDatabaseConnection.H2)
class BookDaoTest {
    private static final int EXPECTED_BOOK_COUNT = 3;
    private static final String TITLE = "t";
    @Autowired
    private BookDao bookDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void shouldInsertBook() {
        Genre genre = em.find(Genre.class, 1L);
        assertThat(genre).isNotNull();
        Author author = em.find(Author.class, 1L);
        assertThat(author).isNotNull();

        Book expectedBook = new Book(0, "aaa", author, genre, null);
        bookDao.save(expectedBook);
        Book actualBook = bookDao.findById(expectedBook.getId()).get();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldGetBookById() {
        Book expectedBook = em.find(Book.class, 1L);
        Book actualBook = bookDao.findById(1L).get();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldReturnExpectedBooksCount() {
        int actualBooksCount = bookDao.findAll().size();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @Test
    void shouldUpdateBook() {
        long bookId = 1L;
        Book book = em.find(Book.class, bookId);
        em.detach(book);
        book.setName(TITLE);
        bookDao.save(book);
        Book updatedBook = em.find(Book.class, bookId);
        assertThat(updatedBook.getName()).isEqualTo(TITLE);
    }

    @Test
    void shouldDeleteBook() {
        Book deleteBook = em.find(Book.class, 1L);
        assertThat(deleteBook).isNotNull();

        bookDao.delete(deleteBook);
        Book deletedBook = em.find(Book.class, 1L);

        assertThat(deletedBook).isNull();
    }
}
