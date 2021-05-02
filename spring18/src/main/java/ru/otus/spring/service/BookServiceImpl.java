package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookDao bookDao;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")},
            fallbackMethod = "getEmptyBook")
    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        bookDao.deleteById(id);
    }

    List<Book> getEmptyBook() {
        return List.of(new Book(0L, "NA", new Author(0L, "NA"), new Genre(0L, "NA")));
    }
}
