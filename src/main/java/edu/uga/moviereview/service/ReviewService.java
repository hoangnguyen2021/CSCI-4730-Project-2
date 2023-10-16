package edu.uga.moviereview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uga.moviereview.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Map<String, Object>> fetchReviews() {
        return reviewRepository.getReviews();
    }

    public List<Map<String, Object>> fetchReviewDetailsAndIds() {
        return reviewRepository.getReviewDetailsAndIds();
    }

    public String fetchUserNameFromReviewId(int id) {
        return reviewRepository.getUserNameFromReviewId(id);
    }

    public List<Map<String, Object>> fetchReviewsWithUserName(String userName) {
        return reviewRepository.getReviewsHavingUserName(userName);
    }

    public List<Map<String, Object>> fetchReviewsWithMovieName(String movieName) {
        return reviewRepository.getReviewsHavingMovieName(movieName);
    }

    public void addReview(String userName, String movieName, int rating, String comment) {
        reviewRepository.addReview(userName, movieName, rating, comment);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteReview(id);
    }

}
