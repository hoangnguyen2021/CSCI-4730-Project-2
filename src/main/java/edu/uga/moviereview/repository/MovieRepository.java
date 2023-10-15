package edu.uga.moviereview.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getMoviesWithGenres() {
        String sql =
            """
            SELECT MovieName, ReleaseDate, Director, Genres.GenreName FROM Movies
            INNER JOIN Movie_Genre ON Movie_Genre.MovieId = Movies.MovieId
            INNER JOIN Genres ON Movie_Genre.GenreID = Genres.GenreId
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getTopRatedMovies() {
        String sql =
            """
            SELECT MovieName, ROUND(AVG(Rating),2) as AverageRating FROM Reviews r
            JOIN Movies m ON r.MovieId = m.MovieId
            GROUP BY MovieName
            ORDER BY AverageRating
            DESC LIMIT 3
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getMovieNames() {
        String sql =
            """
            SELECT MovieName FROM Movies
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getMoviesCountInGenres() {
        String sql = "SELECT Genres.GenreName, COUNT(Movies.MovieId) as MovieCount FROM Genres JOIN Movie_Genre ON Genres.GenreId = Movie_Genre.GenreId JOIN Movies ON Movie_Genre.MovieId = Movies.MovieId GROUP BY Genres.GenreId";
        return jdbcTemplate.queryForList(sql);
    }

    public void insertMovie(String movieName, Date releaseDate, String director) {
        String sql = "INSERT INTO movies (MovieName, ReleaseDate, Director) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, (Object) movieName, releaseDate, director);
    }

    public void deleteMovie(String movieName) {
        String sql = "DELETE FROM movies WHERE MovieName = ? ";
        jdbcTemplate.update(sql, movieName);
    }

    public List<Map<String, Object>> fetchMovieAfterDate(Date ReleaseDate) {
        String sql = "SELECT MovieName, ReleaseDate FROM Movies";
        if (Objects.nonNull(ReleaseDate)) {
            sql += " WHERE ReleaseDate > ?";
            return jdbcTemplate.queryForList(sql,ReleaseDate);
        } else {
            return jdbcTemplate.queryForList(sql);
        }
    }
}
