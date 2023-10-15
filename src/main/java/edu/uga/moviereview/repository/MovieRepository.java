package edu.uga.moviereview.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getTopRatedMovies() {
        String sql =
            """
            SELECT MovieName, AVG(Rating) as AverageRating FROM Reviews r
            JOIN Movies m ON r.MovieId = m.MovieId
            GROUP BY MovieName
            ORDER BY AverageRating
            DESC LIMIT 3
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getMoviesWithGenres() {
        String sql =
            """
            SELECT MovieName, ReleaseDate, Director, Genres.GenreName from Movies
            INNER JOIN Movie_Genre ON Movie_Genre.MovieId = Movies.MovieId
            INNER JOIN Genres ON Movie_Genre.GenreID = Genres.GenreId
            """;
        return jdbcTemplate.queryForList(sql);
    }
}
