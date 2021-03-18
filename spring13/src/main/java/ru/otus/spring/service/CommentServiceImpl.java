package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Comment;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    @Override
    public List<Comment> findAllBookComments(Long bookId) {
        return commentDao.findAllByBookId(bookId);
    }
}
