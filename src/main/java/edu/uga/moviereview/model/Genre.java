package edu.uga.moviereview.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GenreId")
    private int genreId;

    @Column(name = "GenreName", unique = true, nullable = false, length = 50)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
