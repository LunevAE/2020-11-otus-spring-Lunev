package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({CommentDaoImpl.class})
class CommentDaoTest {
    private static final int EXPECTED_COMMENT_COUNT = 2;
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void shouldInsertComment() {
        Book book = em.find(Book.class, 1L);
        assertThat(book).isNotNull();

        Comment expectedComment = new Comment(0, "aaa", book);
        commentDao.insert(expectedComment);
        Comment actualComment = commentDao.getById(expectedComment.getId()).get();
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void shouldGetCommentById() {
        Comment expectedComment = em.find(Comment.class, 1L);
        Comment actualComment = commentDao.getById(1L).get();
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void shouldReturnExpectedBooksCount() {
        int actualBooksCount = commentDao.getAll().size();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_COMMENT_COUNT);
    }

    @Test
    void shouldUpdateBook() {
        long commentId = 1L;
        Comment comment = em.find(Comment.class, commentId);
        em.detach(comment);
        comment.setComment("aaa");
        commentDao.update(comment);
        Comment updatedBook = em.find(Comment.class, commentId);
        assertThat(updatedBook.getComment()).isEqualTo("aaa");
    }

    @Test
    void shouldDeleteBook() {
        long commentId = 1L;
        Comment deleteComment = em.find(Comment.class, commentId);
        assertThat(deleteComment).isNotNull();

        commentDao.delete(deleteComment);
        Comment deletedComment = em.find(Comment.class, commentId);

        assertThat(deletedComment).isNull();
    }
}

