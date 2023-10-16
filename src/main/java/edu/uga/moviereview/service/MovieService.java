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

    public List<Map<String, Object>> fetchMovies() {
        return movieRepository.getMovies();
    }

    public List<Map<String, Object>> fetchGenresForMovie(String movieName) {
        return movieRepository.getGenresForMovie(movieName);
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
        return movieRepository.getMovieAfterDate(releaseDate);
    }

    public void addMovie(String movieName, Date releaseDate, String director) {
        movieRepository.addMovie(movieName, releaseDate, director);
    }

    public void deleteMovie(String movieName) {
        movieRepository.deleteMovie(movieName);
    }
}
