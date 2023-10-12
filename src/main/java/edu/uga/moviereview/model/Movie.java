package edu.uga.moviereview.model;

import java.util.Date;
import java.util.Set;

public class Movie {

    private Long id;
    private String movieName;
    private Date releaseDate;
    private String director;
    private Set<Review> reviews;
    private Double averageRating;

    public Movie() {
    }

    // A full constructor can be handy when mapping result sets to objects in JDBC
    public Movie(Long id, String movieName, Date releaseDate, String director, Double averageRating) {
        this.id = id;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.director = director;
        this.averageRating = averageRating;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
