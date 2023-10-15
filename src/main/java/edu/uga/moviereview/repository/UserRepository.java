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
            SELECT EXISTS(SELECT UserName FROM Users WHERE UserName = ?)
            """;
        return jdbcTemplate.queryForObject(sql, Boolean.class, username);
    }

    public boolean hasEmail(String email) {
        String sql =
            """
            SELECT EXISTS(SELECT Email FROM Users WHERE Email = ?)
            """;
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    public boolean isPasswordCorrect(String username, String password) {
        String sql =
            """
            SELECT EXISTS(
                SELECT UserName FROM Users
                WHERE UserName = ?
                AND PasswordHash = ?)
            """;
        return jdbcTemplate.queryForObject(sql, Boolean.class, username, password);
    }

    public void addUser(String username, String password, String email) {
        String sql =
            """
            INSERT INTO Users (UserName, Email, RegistrationDate, PasswordHash) VALUES
            (
                ?,
                ?,
                CURDATE(),
                ?
            )
            """;
        jdbcTemplate.update(sql, username, email, password);
    }

}
