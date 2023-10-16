package edu.uga.moviereview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uga.moviereview.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void addGenre(String genreName) {
        genreRepository.addGenre(genreName);
    }

    public List<Map<String, Object>> fetchGenreNames() {
        return genreRepository.getGenreNames();
    }
}
