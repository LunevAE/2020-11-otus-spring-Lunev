package ru.otus.spring.domain.nosql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("BOOKS")
public class BookDocument {
    @Id
    private String id;
    private String title;
    private AuthorDocument author;
    private GenreDocument genre;
    @Transient
    private List<CommentDocument> comments;
}
