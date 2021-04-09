package ru.otus.spring.dao.sql;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.sql.Book;

@Repository
public interface BookDao extends PagingAndSortingRepository<Book, Long> {
}
