package com.reactproject.react.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactproject.react.model.User;
import com.reactproject.react.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
	 public User saveUser(User user) {
	        // You can add additional logic like hashing password here
	        return userRepository.save(user);
	    }

	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }
	    @Override
	    public User resetPassword(String email, String newPassword, String confirmPassword) {
	        if (!newPassword.equals(confirmPassword)) {
	            throw new IllegalArgumentException("Passwords do not match.");
	        }

	        User user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new RuntimeException("User not found with email: " + email);
	        }

	        user.setPassword(newPassword); // Optional: encode it
	        return userRepository.save(user);
	    }
}
