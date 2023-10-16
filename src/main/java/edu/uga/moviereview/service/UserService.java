package edu.uga.moviereview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uga.moviereview.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<Map<String, Object>> fetchUsersSecure() {
        return userRepository.getUsersSecure();
    }

    public boolean userExists(String username) {
        return userRepository.hasUser(username);
    }

    public boolean emailExists(String email) {
        return userRepository.hasEmail(email);
    }

    public boolean isPasswordCorrect(String username, String password) {
        return userRepository.isPasswordCorrect(username, password);
    }

    public void addUser(String username, String password, String email) {
        userRepository.addUser(username, password, email);
    }
}
