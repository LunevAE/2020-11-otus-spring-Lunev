package ru.otus.spring.dao;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private MongoTemplate mt;

    @Test
    void shouldInsertBook() {
        Genre genre = mt.findById("1", Genre.class);
        assertThat(genre).isNotNull();
        Author author = mt.findById("1", Author.class);
        assertThat(author).isNotNull();

        Book expectedBook = new Book("1", "aaa", author, genre);
        bookDao.save(expectedBook);
        Book actualBook = bookDao.findById(expectedBook.getId()).get();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldGetBookById() {
        Book expectedBook = mt.findById("1", Book.class);
        Book actualBook = bookDao.findById("1").get();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void shouldDeleteBook() {
        Book deleteBook = mt.findById("1", Book.class);
        assertThat(deleteBook).isNotNull();

        bookDao.delete(deleteBook);
        Book deletedBook = mt.findById("1", Book.class);

        assertThat(deletedBook).isNull();
    }
}
