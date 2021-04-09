package ru.otus.spring.dao.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.nosql.BookDocument;

@Repository
public interface BookDocumentDao extends MongoRepository<BookDocument, String> {
}
