package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentDao commentDao;
    private final BookDao bookDao;

    public CommentServiceImpl(CommentDao commentDao, BookDao bookDao) {
        this.commentDao = commentDao;
        this.bookDao = bookDao;
    }

    @Transactional
    @Override
    public void insertComment(Long bookId, String comment) {
        Book book = bookDao.getById(bookId).get();
        commentDao.insert(new Comment(0, comment, book));
    }

    @Transactional(readOnly = true)
    @Override
    public void getComments(Long bookId) {
        Book book = bookDao.getById(bookId).get();
        List<Comment> comments = book.getComments();
        for (Comment comment : comments) {
            System.out.println(comment.toString());
        }
    }

    @Transactional
    @Override
    public void updateComment(Long id, String comment) {
        Comment c = commentDao.getById(id).get();
        c.setComment(comment);
        commentDao.update(c);
    }

    @Transactional
    @Override
    public void deleteComment(Long id) {
        Comment comment = commentDao.getById(id).get();
        commentDao.delete(comment);
    }
}
