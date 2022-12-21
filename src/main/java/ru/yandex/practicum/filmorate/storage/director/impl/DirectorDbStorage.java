package ru.yandex.practicum.filmorate.storage.director.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.storage.director.DirectorStorage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Repository
public class DirectorDbStorage implements DirectorStorage {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DirectorDbStorage(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Director save(Director director) {
        String sqlQuery = "INSERT INTO DIRECTORS ( NAME) " +
                "VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"DIRECTOR_ID"});
            stmt.setString(1, director.getName());
            return stmt;
        }, keyHolder);
        director.setId(keyHolder.getKey().intValue());
        return director;
    }

    @Override
    public Director update(Director director) {
        String sqlQuery = "UPDATE DIRECTORS SET NAME = ? WHERE DIRECTOR_ID = ?";
        jdbcTemplate.update(sqlQuery, director.getName(), director.getId());
        return director;
    }

    @Override
    public Collection<Director> findAll() {
        String sqlQuery = "SELECT DIRECTOR_ID, NAME FROM DIRECTORS";
        return jdbcTemplate.query(sqlQuery, this::mapRowToDirector);
    }

    @Override
    public Optional<Director> findDirectorById(int id) {
        String sqlQuery = "SELECT DIRECTOR_ID, NAME FROM DIRECTORS WHERE DIRECTOR_ID = ?";

        SqlRowSet directorRows = jdbcTemplate.queryForRowSet(sqlQuery, id);
        if(directorRows.next()) {
            Director director = Director.builder()
                    .id(directorRows.getInt("DIRECTOR_ID"))
                    .name(directorRows.getString("NAME"))
                    .build();
            log.info("Найден режиссер с id: {}, по имени {} ", directorRows.getInt("DIRECTOR_ID"),
                    directorRows.getString("NAME"));
            return Optional.of(director);
        } else {
            log.info("Режиссер id {} не найден", id);
            return Optional.empty();
        }
    }

    @Override
    public void deleteDirector(int id) {
        String sql = "DELETE FROM DIRECTORS WHERE DIRECTOR_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public Director mapRowToDirector(ResultSet resultSet, int i) throws SQLException {
        return Director.builder()
                .id(resultSet.getInt("DIRECTOR_ID"))
                .name(resultSet.getString("NAME"))
                .build();
    }
}
