package ru.otus.spring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    private Author author1;
    private Author author2;
    private Genre genre1;
    private Genre genre2;
    private Book book1;
    private Book book2;

    @ChangeSet(order = "000", id = "dropDB", author = "lunev", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthor", author = "lunev", runAlways = true)
    public void initAuthors(AuthorDao authorDao) {
        author1 = authorDao.save(new Author("1", "Harper Lee"));
        author2 = authorDao.save(new Author("2", "Arthur Conan Doyle"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "lunev", runAlways = true)
    public void initGenres(GenreDao genreDao) {
        genre1 = genreDao.save(new Genre("1", "Novel"));
        genre2 = genreDao.save(new Genre("2", "Detective"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "lunev", runAlways = true)
    public void initBooks(BookDao bookDao) {
        book1 = new Book("1", "To Kill a Mockingbird", author1, genre1);
        bookDao.save(book1);

        book2 = new Book("2", "A Study in Scarlet", author2, genre2);
        bookDao.save(book2);
    }

    @ChangeSet(order = "004", id = "initComments", author = "lunev", runAlways = true)
    public void initBooks(CommentDao commentDao) {
        Comment comment1 = new Comment("1", "Nice", book1);
        commentDao.save(comment1);

        Comment comment2 = new Comment("2", "Great", book1);
        commentDao.save(comment2);
    }
}
