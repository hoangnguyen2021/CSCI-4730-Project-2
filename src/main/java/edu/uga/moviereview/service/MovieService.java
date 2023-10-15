package edu.uga.moviereview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uga.moviereview.repository.MovieRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Map<String, Object>> fetchMoviesWithGenres() {
        return movieRepository.getMoviesWithGenres();
    }

    public List<Map<String, Object>> fetchTopRatedMovies() {
        return movieRepository.getTopRatedMovies();
    }

    public List<Map<String, Object>> fetchMovieNames() {
        return movieRepository.getMovieNames();
    }

    public List<Map<String, Object>> fetchMoviesCountInGenres() {
        return movieRepository.getMoviesCountInGenres();
    }

    public List<Map<String, Object>> fetchMovieAfterDate(Date releaseDate){
        return movieRepository.fetchMovieAfterDate(releaseDate);
    }

    public void insertMovie(String movieName, Date releaseDate, String director) {
        movieRepository.insertMovie(movieName, releaseDate, director);
    }

    public void deleteMovie(String movieName) {
        movieRepository.deleteMovie(movieName);
    }
}
