package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Book(Long id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
