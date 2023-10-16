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

    public List<Map<String, Object>> getMovies() {
        String sql =
            """
            SELECT MovieName, ReleaseDate, Director FROM Movies
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getGenresForMovie(String movieName) {
        String sql =
            """
            SELECT GenreName FROM Genres
            INNER JOIN Movie_Genre ON Movie_Genre.GenreId = Genres.GenreId
            INNER JOIN Movies ON Movies.MovieId = Movie_Genre.MovieId AND Movies.MovieName = ?
            """;
        return jdbcTemplate.queryForList(sql, movieName);
    }

    public List<Map<String, Object>> getTopRatedMovies() {
        String sql =
            """
            SELECT DISTINCT MovieName, ROUND(AVG(Rating),2) as AverageRating FROM Reviews r
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

    public void addMovie(String movieName, Date releaseDate, String director) {
        String sql = "INSERT INTO Movies (MovieName, ReleaseDate, Director) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, (Object)movieName, releaseDate, director);
    }

    public void deleteMovie(String movieName) {
        String sql = "DELETE FROM Movies WHERE MovieName = ?";
        jdbcTemplate.update(sql, movieName);
    }

    public List<Map<String, Object>> getMovieAfterDate(Date ReleaseDate) {
        String sql = "SELECT MovieName, ReleaseDate FROM Movies";
        if (Objects.nonNull(ReleaseDate)) {
            sql += " WHERE ReleaseDate > ?";
            return jdbcTemplate.queryForList(sql, ReleaseDate);
        }
        else {
            return jdbcTemplate.queryForList(sql);
        }
    }
}
