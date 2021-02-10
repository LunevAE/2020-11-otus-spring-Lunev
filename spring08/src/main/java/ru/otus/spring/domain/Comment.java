package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("comments")
public class Comment {
    @Id
    private String id;
    private String comment;

    @DBRef
    private Book book;

    @Override
    public String toString() {
        return "id=" + id +
                ", comment='" + comment + '\'' +
                ", book=" + book.getName();
    }
}
