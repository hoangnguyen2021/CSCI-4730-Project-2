package edu.uga.moviereview.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.uga.moviereview.service.ReviewService;

@Controller
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public String getReviews(Model model, @RequestParam(required = false) String userName) {
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
}
