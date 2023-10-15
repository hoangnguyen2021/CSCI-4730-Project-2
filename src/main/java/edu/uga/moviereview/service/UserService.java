package edu.uga.moviereview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uga.moviereview.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public boolean userExists(String username) {
        return userRepository.hasUser(username);
    }

    public boolean isPasswordCorrect(String username, String password) {
        return userRepository.isPasswordCorrect(username, password);
    }

}
