package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl implements GenreDao{
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Genre genre) {
        String sql = "insert into GENRES (ID, GENRE) values (:ID, :GENRE)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", genre.getId())
                .addValue("GENRE", genre.getGenre());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public Genre getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("ID", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, GENRE from GENRES where ID = :ID", params, new GenreDaoImpl.GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query("select ID, GENRE from GENRES", new GenreDaoImpl.GenreMapper());
    }

    @Override
    public void update(Genre genre) {
        String sql = "update GENRES set GENRE = :GENRE where ID = :ID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", genre.getId())
                .addValue("GENRE", genre.getGenre());
        namedParameterJdbcOperations.update(sql, params);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from GENRES where ID = :ID";
        Map<String, Object> params = Collections.singletonMap("ID", id);
        namedParameterJdbcOperations.update(sql, params);
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Genre(resultSet.getLong("ID"), resultSet.getString("GENRE"));
        }
    }
}
