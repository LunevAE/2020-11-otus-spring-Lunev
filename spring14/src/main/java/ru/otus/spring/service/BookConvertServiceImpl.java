package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.nosql.BookDocument;
import ru.otus.spring.domain.sql.Book;
import org.modelmapper.ModelMapper;

@Service
public class BookConvertServiceImpl implements BookConvertService {

    @Override
    public BookDocument convert(Book book) {
        ModelMapper mapper = new ModelMapper();
        BookDocument bookDocument = mapper.map(book, BookDocument.class);
        return bookDocument;
    }
}
