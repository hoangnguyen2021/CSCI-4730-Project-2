package edu.uga.moviereview.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieId")
    private int movieId;

    @Column(name = "MovieName", nullable = false)
    private String movieName;

    @Column(name = "ReleaseDate", nullable = false)
    private Date releaseDate;

    @Column(name = "Director")
    private String director;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Movie_Genre",
            joinColumns = {@JoinColumn(name = "MovieId")},
            inverseJoinColumns = {@JoinColumn(name = "GenreId")})
    private Set<Genre> genres;

    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews = new LinkedHashSet<>();

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
