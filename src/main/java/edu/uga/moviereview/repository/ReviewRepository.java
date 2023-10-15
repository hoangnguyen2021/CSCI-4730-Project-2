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
            INNER JOIN Movies ON Reviews.MovieId = Movies.MovieId
            INNER JOIN Users ON Users.UserId = Reviews.UserId
            """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getReviewsWithUserName(String userName) {
        String sql =
            """
            SELECT Movies.MovieName, Users.UserName, Rating, Comment, ReviewDate FROM Reviews
            INNER JOIN Movies ON Reviews.MovieId = Movies.MovieId
            INNER JOIN Users ON Users.UserId = Reviews.UserId AND Users.UserName = \"%s\"
            """.formatted(userName);
        return jdbcTemplate.queryForList(sql);
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
}
