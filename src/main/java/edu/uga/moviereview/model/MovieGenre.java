package edu.uga.moviereview.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Movie_Genre")
@IdClass(MovieGenreId.class)
public class MovieGenre {
    @Id
    @Column(name = "MovieId")
    private int movieId;

    @Id
    @Column(name = "GenreId")
    private int genreId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
