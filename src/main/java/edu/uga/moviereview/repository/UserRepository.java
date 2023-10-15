package edu.uga.moviereview.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean hasUser(String username) {
        String sql =
            """
            SELECT EXISTS(SELECT UserName FROM Users WHERE UserName = \"%s\")
            """.formatted(username);
        return jdbcTemplate.queryForObject(sql, Boolean.class);
    }

    public boolean isPasswordCorrect(String username, String password) {
        String sql =
            """
            SELECT EXISTS(
                SELECT UserName FROM Users
                WHERE UserName = \"%s\"
                AND PasswordHash = \"%s\")
            """.formatted(username, password);
        return jdbcTemplate.queryForObject(sql, Boolean.class);
    }

}
