package ru.otus.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENRES")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "genre", nullable = false, unique = true)
    private String genre;

    public Genre() {
    }

    public Genre(long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }
}
