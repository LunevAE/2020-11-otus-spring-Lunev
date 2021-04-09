package ru.otus.spring.service;

import ru.otus.spring.domain.nosql.BookDocument;
import ru.otus.spring.domain.sql.Book;

public interface BookConvertService {
    BookDocument convert(Book book);
}
