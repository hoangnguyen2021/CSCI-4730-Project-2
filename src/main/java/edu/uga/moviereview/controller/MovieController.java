package edu.uga.moviereview.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.uga.moviereview.service.MovieService;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String loadMoviesPage(Model model) {
        List<Map<String, Object>> movies = movieService.fetchMoviesWithGenres();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/top-rated-movies")
    public String loadTopRatedMoviesPage(Model model) {
        List<Map<String, Object>> topRatedMovies = movieService.fetchTopRatedMovies();
        model.addAttribute("movies", topRatedMovies);
        return "top-rated-movies";  // This refers to the 'top-rated-movies' view (e.g., a Mustache or Thymeleaf template)
    }

    @GetMapping("/new-movie")
    public String loadNewMoviePage(Model model) {
        List<Map<String, Object>> movieNames = movieService.fetchMovieNames();
        model.addAttribute("movie-names", movieNames);
        return "new-movie";
    }

    @GetMapping("/new-review")
    public String loadNewReviewPage() {
        return null;
    }
}
