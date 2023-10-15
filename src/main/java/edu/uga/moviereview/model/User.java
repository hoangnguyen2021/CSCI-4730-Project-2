package edu.uga.moviereview.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userId;

    @Column(name = "UserName", unique = true, nullable = false, length = 100)
    private String userName;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "RegistrationDate", nullable = false)
    private Date registrationDate;

    @Column(name = "PasswordHash", nullable = false)
    private String passwordHash;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
