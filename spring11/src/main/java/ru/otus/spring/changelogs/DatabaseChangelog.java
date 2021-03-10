package ru.otus.spring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
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
    public void initAuthors(MongockTemplate template) {
        author1 = template.save(new Author("1", "Harper Lee"));
        author2 = template.save(new Author("2", "Arthur Conan Doyle"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "lunev", runAlways = true)
    public void initGenres(MongockTemplate template) {
        genre1 = template.save(new Genre("1", "Novel"));
        genre2 = template.save(new Genre("2", "Detective"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "lunev", runAlways = true)
    public void initBooks(MongockTemplate template) {
        book1 = new Book("1", "To Kill a Mockingbird", author1, genre1);
        template.save(book1);

        book2 = new Book("2", "A Study in Scarlet", author2, genre2);
        template.save(book2);
    }

}
