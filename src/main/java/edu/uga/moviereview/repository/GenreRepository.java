package edu.uga.moviereview.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addGenre(String genreName) {
        String sql =
            """
            INSERT INTO Genres (GenreName) VALUES (?)
            """;
        jdbcTemplate.update(sql, genreName);
    }

    public List<Map<String, Object>> getGenreNames() {
        String sql =
            """
            SELECT GenreName FROM Genres
            """;
        return jdbcTemplate.queryForList(sql);
    }

}
