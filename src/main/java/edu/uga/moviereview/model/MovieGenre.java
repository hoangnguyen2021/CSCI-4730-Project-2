package edu.uga.moviereview.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Movie_Genre")
@IdClass(MovieGenreId.class)
public class MovieGenre {
    @EmbeddedId
    private MovieGenreId id;

    @OneToOne
    @MapsId("movieId")
    @JoinColumn(name = "MovieId")
    private Movie movie;

    @OneToOne
    @MapsId("genreId")
    @JoinColumn(name = "GenreId")
    private Genre genre;
}
