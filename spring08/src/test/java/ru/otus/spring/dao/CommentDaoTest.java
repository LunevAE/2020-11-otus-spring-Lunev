package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class CommentDaoTest {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private MongoTemplate mt;

    @Test
    void shouldInsertComment() {
        Book book = mt.findById("2", Book.class);
        assertThat(book).isNotNull();

        Comment expectedComment = new Comment("1", "aaa", book);
        commentDao.save(expectedComment);
        Comment actualComment = commentDao.findById(expectedComment.getId()).get();
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void shouldGetCommentById() {
        Comment expectedComment = mt.findById("1", Comment.class);
        Comment actualComment = commentDao.findById("1").get();
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }


    @Test
    void shouldDeleteComment() {
        String commentId = "2";
        Comment deleteComment = mt.findById(commentId, Comment.class);
        assertThat(deleteComment).isNotNull();

        commentDao.delete(deleteComment);
        Comment deletedComment = mt.findById(commentId, Comment.class);

        assertThat(deletedComment).isNull();
    }
}

