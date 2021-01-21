package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

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
        String sql = "insert into BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values " +
                     "(:ID, :NAME, :AUTHOR_ID, :GENRE_ID)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", book.getId());
        params.addValue("NAME", book.getName());
        params.addValue("AUTHOR_ID", book.getAuthor().getId());
        params.addValue("GENRE_ID", book.getGenre().getId());
        namedParameterJdbcOperations.update(sql, params);
    }


    @Override
    public Book getById(Long id) {
        String sql = "select BOOKS.ID, BOOKS.NAME, BOOKS.AUTHOR_ID, BOOKS.GENRE_ID, AUTHORS.NAME, GENRES.GENRE " +
                "from BOOKS " +
                "inner join AUTHORS on BOOKS.AUTHOR_ID = AUTHORS.ID " +
                "inner join GENRES on BOOKS.GENRE_ID = GENRES.ID " +
                "where BOOKS.ID = :ID";
        Map<String, Object> params = Collections.singletonMap("ID", id);
        return namedParameterJdbcOperations.queryForObject(
                sql, params, new BookDaoImpl.BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        String sql = "select BOOKS.ID, BOOKS.NAME, BOOKS.AUTHOR_ID, BOOKS.GENRE_ID, AUTHORS.NAME, GENRES.GENRE " +
                     "from BOOKS " +
                     "inner join AUTHORS on BOOKS.AUTHOR_ID = AUTHORS.ID " +
                     "inner join GENRES on BOOKS.GENRE_ID = GENRES.ID";
        return namedParameterJdbcOperations.getJdbcOperations().query(sql, new BookDaoImpl.BookMapper());
    }

    @Override
    public void update(Book book) {
        String sql = "update BOOKS set NAME = :NAME, AUTHOR_ID = :AUTHOR_ID, GENRE_ID = :GENRE_ID  " +
                     "where ID = :ID";
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
            return new Book(resultSet.getLong("ID"), resultSet.getString("NAME"),
                   new Author(resultSet.getLong("AUTHOR_ID"),  resultSet.getString("AUTHORS.NAME")),
                   new Genre(resultSet.getLong("GENRE_ID"), resultSet.getString("GENRE")));
        }
    }
}
