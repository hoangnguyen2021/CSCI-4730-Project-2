package edu.uga.moviereview.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.uga.moviereview.model.Review;
import edu.uga.moviereview.service.GenreService;
import edu.uga.moviereview.service.MovieService;
import edu.uga.moviereview.service.ReviewService;
import edu.uga.moviereview.service.UserService;

@Controller
public class UnifiedController {
    
    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

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
    public String loadNewMoviePage() {
        return null;
    }

    @GetMapping("/new-review")
    public String loadNewReviewPage(Model model) {
        List<Map<String, Object>> movieNames = movieService.fetchMovieNames();
        model.addAttribute("movie-names", movieNames);
        model.addAttribute("error-message", "");
        return "new-review";
    }

    @GetMapping("/movies-after-date")
    public String fetchMovieAfterDate(Model model, @RequestParam(required = false) String releaseDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parseDate = null;
            if (releaseDate != null && !"".equals(releaseDate)){
                parseDate = format.parse(releaseDate);
            }
            List<Map<String, Object>> maps = movieService.fetchMovieAfterDate(parseDate);
            model.addAttribute("movies", maps);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "movies-after-date";
    }

    @GetMapping("/movie-with-genres")
    public String fetchMoviesWithGenres(Model model) {
        List<Map<String, Object>> moviesWithGenres = movieService.fetchMoviesCountInGenres();
        model.addAttribute("movies", moviesWithGenres);
        return "movie-with-genres";
    }

    @GetMapping("/insert-movie-mustache")
    public String insertMovie() {
        return "/insert-movie";
    }

    @GetMapping("/insert-movie")
    public String insertMovie(@RequestParam String movieName, @RequestParam String releaseDate, @RequestParam String director) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parseDate = format.parse(releaseDate);
            movieService.insertMovie(movieName, parseDate, director);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "insert-movie";
    }

    @GetMapping("/delete-movie-mustache")
    public String deleteMovie() {
        return "/delete-movie";
    }

    @GetMapping("/delete-movie")
    public String deleteMovie(@RequestParam String movieName) {
        movieService.deleteMovie(movieName);
        return "delete-movie";
    }

    @GetMapping("/new-genre")
    public String loadNewGenrePage(Model model) {
        List<Map<String, Object>> genreNames = genreService.fetchGenreNames();
        model.addAttribute("genre-names", genreNames);
        return "/new-genre";
    }

    @GetMapping("/reviews")
    public String loadReviewsPage(Model model, @RequestParam(required = false) String userName) {
        if (userName == null)
        {
            List<Map<String, Object>> reviews = reviewService.fetchReviews();
            model.addAttribute("reviews", reviews);
            return "reviews";
        }
        else
        {
            List<Map<String, Object>> reviews = reviewService.fetchReviewsWithUserName(userName);
            model.addAttribute("reviews", reviews);
            return "reviews";
        }
    }

    @PostMapping("/new-review")
    public String submitNewReviewPage(Model model,
        @ModelAttribute("username") String username,
        @ModelAttribute("password") String password,
        @ModelAttribute("movie-name") String movieName,
        @ModelAttribute("rating") int rating,
        @ModelAttribute("comment") String comment) {

        boolean userExists = userService.userExists(username);
        boolean isPasswordCorrect = userService.isPasswordCorrect(username, password);

        if (!userExists || !isPasswordCorrect) {
            model.addAttribute("error-message", userExists ? "Password is incorrect." : "Username doesn't exist.");
            List<Map<String, Object>> movieNames = movieService.fetchMovieNames();
            model.addAttribute("movie-names", movieNames);
            return "new-review";
        }
        
        reviewService.addReview(username, movieName, rating, comment);

        List<Map<String, Object>> movieNames = movieService.fetchMovieNames();
        model.addAttribute("movie-names", movieNames);
        model.addAttribute("error-message", "");
        return "new-review";
    }
}
