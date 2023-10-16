package edu.uga.moviereview.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.uga.moviereview.service.GenreService;
import edu.uga.moviereview.service.MovieService;
import edu.uga.moviereview.service.ReviewService;
import edu.uga.moviereview.service.UserService;

@Controller
public class UnifiedController {
    
    @Autowired
    private MovieService movieService;

    //@Autowired
    //private GenreService genreService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/movies")
    public String getMoviesPage(Model model) {
        List<Map<String, Object>> movies = movieService.fetchMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/top-rated-movies")
    public String getTopRatedMoviesPage(Model model) {
        List<Map<String, Object>> topRatedMovies = movieService.fetchTopRatedMovies();
        model.addAttribute("movies", topRatedMovies);
        return "top-rated-movies";  // This refers to the 'top-rated-movies' view (e.g., a Mustache or Thymeleaf template)
    }

    @GetMapping("/reviews")
    public String getReviewsPage(Model model,
        @RequestParam(name = "user-name", required = false) String userName,
        @RequestParam(name = "movie-name", required = false) String movieName) {

        if (userName != null && movieName != null) {
            // shouldn't get here, but if it does then default
            List<Map<String, Object>> reviews = reviewService.fetchReviews();
            model.addAttribute("reviews", reviews);
            return "reviews";
        }
        else if (userName != null) {
            List<Map<String, Object>> reviews = reviewService.fetchReviewsWithUserName(userName);
            model.addAttribute("reviews", reviews);
            return "reviews";
        }
        else if (movieName != null) {
            List<Map<String, Object>> reviews = reviewService.fetchReviewsWithMovieName(movieName);
            model.addAttribute("reviews", reviews);
            return "reviews";
        }
        else {
            List<Map<String, Object>> reviews = reviewService.fetchReviews();
            model.addAttribute("reviews", reviews);
            return "reviews";
        }
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<Map<String, Object>> users = userService.fetchUsersSecure();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new-user")
    public String getNewUserPage(Model model) {
        model.addAttribute("error-message", "");
        return "new-user";
    }

    @PostMapping("/new-user")
    public String postNewUserPage(Model model,
        @ModelAttribute("username") String username,
        @ModelAttribute("password") String password,
        @ModelAttribute("email") String email) {
        
        if (userService.userExists(username)) {
            model.addAttribute("error-message", "Username already exists.");
            return "new-user";
        }

        if (userService.emailExists(email)) {
            model.addAttribute("error-message", "Email already registered.");
            return "new-user";
        }

        userService.addUser(username, password, email);
        model.addAttribute("error-message", "");
        return "new-user";
    }

    @GetMapping("/new-movie")
    public String getNewMoviePage(Model model) {
        model.addAttribute("error-message", "");
        return "new-movie";
    }

    @PostMapping("/new-movie")
    public String postNewMoviePage(Model model,
        @ModelAttribute("username") String username,
        @ModelAttribute("password") String password,
        @ModelAttribute("movie-name") String movieName,
        @ModelAttribute("release-date") Date releaseDate,
        @ModelAttribute("director") String director) {
        
        boolean userExists = userService.userExists(username);
        boolean isPasswordCorrect = userService.isPasswordCorrect(username, password);

        if (!userExists || !isPasswordCorrect) {
            model.addAttribute("error-message", userExists ? "Password is incorrect." : "Username doesn't exist.");
            return "new-movie";
        }

        movieService.addMovie(movieName, releaseDate, director);

        model.addAttribute("error-message", "");
        return "new-movie";
    }

    @GetMapping("/new-review")
    public String getNewReviewPage(Model model) {
        List<Map<String, Object>> movieNames = movieService.fetchMovieNames();
        model.addAttribute("movie-names", movieNames);
        model.addAttribute("error-message", "");
        return "new-review";
    }

    @PostMapping("/new-review")
    public String postNewReviewPage(Model model,
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

    @GetMapping("/new-genre")
    public String getNewGenrePage(Model model) {
        return null;
    }
}
