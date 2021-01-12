package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao{
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public void insert(Book book) {
        String sql = "insert into BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (:ID, :NAME, :AUTHOR_ID, :GENRE_ID)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", book.getId());
        params.addValue("NAME", book.getName());
        params.addValue("AUTHOR_ID", book.getAuthor().getId());
        params.addValue("GENRE_ID", book.getGenre().getId());
        namedParameterJdbcOperations.update(sql, params);
    }


    @Override
    public Book getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("ID", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, NAME, AUTHOR_ID, GENRE_ID from BOOKS where ID = :ID", params, new BookDaoImpl.BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query("select ID, NAME, AUTHOR_ID, GENRE_ID from BOOKS", new BookDaoImpl.BookMapper());
    }

    @Override
    public void update(Book book) {
        String sql = "update BOOKS set NAME = :NAME, AUTHOR_ID = :AUTHOR_ID, GENRE_ID = :GENRE_ID  where ID = :ID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", book.getId())
                .addValue("NAME", book.getName())
                .addValue("AUTHOR_ID", book.getAuthor().getId())
                .addValue("GENRE_ID", book.getGenre().getId());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from BOOKS where ID = :ID";
        Map<String, Object> params = Collections.singletonMap("ID", id);
        namedParameterJdbcOperations.update(sql, params);
    }

    private class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            long authorId = resultSet.getLong("AUTHOR_ID");
            long genreId = resultSet.getLong("GENRE_ID");
            return new Book(id, name, authorDao.getById(authorId), genreDao.getById(genreId));
        }
    }
}
