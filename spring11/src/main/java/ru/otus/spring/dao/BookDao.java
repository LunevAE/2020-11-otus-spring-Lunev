package ru.otus.spring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Book;

public interface BookDao extends ReactiveMongoRepository<Book, String> {
}
