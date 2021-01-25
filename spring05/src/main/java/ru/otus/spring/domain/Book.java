package ru.otus.spring.domain;

public class Book {
    private final long id;
    private String name;
    private final Author author;
    private final Genre genre;

    public Book(long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author.getName() + '\'' +
                ", genre='" + genre.getGenre() + '\'';
    }
}
