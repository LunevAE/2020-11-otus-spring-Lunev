package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Author author) {
        String sql = "insert into AUTHORS (ID, NAME) values (:ID, :NAME)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", author.getId())
                .addValue("NAME", author.getName());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public Author getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("ID", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, NAME from AUTHORS where ID = :ID", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query("select ID, NAME from AUTHORS", new AuthorMapper());
    }

    @Override
    public void update(Author author) {
        String sql = "update AUTHORS set NAME = :NAME where ID = :ID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", author.getId())
                .addValue("NAME", author.getName());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from AUTHORS where ID = :ID";
        Map<String, Object> params = Collections.singletonMap("ID", id);
        namedParameterJdbcOperations.update(sql, params);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getLong("id"), resultSet.getString("name"));
        }
    }
}

