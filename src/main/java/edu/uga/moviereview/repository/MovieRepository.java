package edu.uga.moviereview.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getTopRatedMovies() {
        String sql = "SELECT MovieName, AVG(Rating) as AverageRating FROM Reviews r JOIN Movies m ON r.MovieId = m.MovieId GROUP BY MovieName ORDER BY AverageRating DESC LIMIT 3";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getMovies() {
        String sql = "SELECT MovieName, ReleaseDate, Director FROM Movies";
        return jdbcTemplate.queryForList(sql);
    }
}
