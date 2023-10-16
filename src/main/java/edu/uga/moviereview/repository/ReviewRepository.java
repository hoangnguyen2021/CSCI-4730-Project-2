package edu.uga.moviereview.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getReviews() {
        String sql =
            """
            SELECT Movies.MovieName, Users.UserName, Rating, Comment, ReviewDate FROM Reviews
            INNER JOIN Movies ON Movies.MovieId = Reviews.MovieId
            INNER JOIN Users ON Users.UserId = Reviews.UserId
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getReviewDetailsAndIds() {
        String sql =
            """
            SELECT ReviewId, Movies.MovieName, Users.UserName, Rating, ReviewDate FROM Reviews
            INNER JOIN Movies ON Movies.MovieId = Reviews.MovieId
            INNER JOIN Users ON Users.UserId = Reviews.UserId
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public String getUserNameFromReviewId(int id) {
        String sql =
            """
            SELECT UserName FROM Users
            INNER JOIN Reviews ON Reviews.UserId = Users.UserId AND Reviews.ReviewId = ?
            LIMIT 1
            """;
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    public List<Map<String, Object>> getReviewsHavingUserName(String userName) {
        String sql =
            """
            SELECT Movies.MovieName, Users.UserName, Rating, Comment, ReviewDate FROM Reviews
            INNER JOIN Movies ON Movies.MovieId = Reviews.MovieId
            INNER JOIN Users ON Users.UserId = Reviews.UserId AND Users.UserName = ?
            """;
        return jdbcTemplate.queryForList(sql, userName);
    }

    public List<Map<String, Object>> getReviewsHavingMovieName(String movieName) {
        String sql =
            """
            SELECT Movies.MovieName, Users.UserName, Rating, Comment, ReviewDate FROM Reviews
            INNER JOIN Movies ON Movies.MovieId = Reviews.MovieId AND Movies.MovieName = ?
            INNER JOIN Users ON Users.UserId = Reviews.UserId
            """;
        return jdbcTemplate.queryForList(sql, movieName);
    }

    public void addReview(String userName, String movieName, int rating, String comment) {
        String sql =
            """
            INSERT INTO Reviews (UserId, MovieId, Rating, Comment, ReviewDate) VALUES
            (
                (SELECT UserId FROM Users WHERE UserName = ? LIMIT 1),
                (SELECT MovieId FROM Movies WHERE MovieName = ? LIMIT 1),
                ?,
                ?,
                CURDATE()
            )
            """;
        jdbcTemplate.update(sql, userName, movieName, rating, comment);
    }

    public void deleteReview(int id) {
        String sql =
            """
            DELETE FROM Reviews WHERE ReviewId = ? LIMIT 1
            """;
        jdbcTemplate.update(sql, id);
    }
}
