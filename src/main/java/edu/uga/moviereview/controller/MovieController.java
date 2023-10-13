package edu.uga.moviereview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.uga.moviereview.service.MovieService;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/top-rated-movies")
    public String getTopRatedMovies(Model model) {
        List<Map<String, Object>> topRatedMovies = movieService.fetchTopRatedMovies();
        model.addAttribute("movies", topRatedMovies);
        return "top-rated-movies";  // This refers to the 'top-rated-movies' view (e.g., a Mustache or Thymeleaf template)
    }
}
