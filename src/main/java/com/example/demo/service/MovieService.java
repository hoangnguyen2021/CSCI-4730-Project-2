package com.example.demo.service;

import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Map<String, Object>> fetchTopRatedMovies() {
        return movieRepository.getTopRatedMovies();
    }
}
