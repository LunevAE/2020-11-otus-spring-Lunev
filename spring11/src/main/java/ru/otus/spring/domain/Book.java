package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("books")
public class Book {
    @Id
    private String id;
    private String title;
    private Author author;
    private Genre genre;

    public String printAuthorAndGenre() {
        return ", author='" + author.getName() + '\'' +
                ", genre='" + genre.getGenre() + '\'';
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", title='" + title + '\'';
    }
}
