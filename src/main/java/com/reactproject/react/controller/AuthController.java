package com.reactproject.react.controller;

import com.reactproject.react.model.ResetPasswordRequest;
import com.reactproject.react.model.User;
import com.reactproject.react.security.JwtUtil;
import com.reactproject.react.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping("/welcome")
    public String welcomeMessage() {
        return " Welcome to the API! Your backend is working.";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User registered successfully!";
    }
    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            userService.resetPassword(request.getEmail(), request.getNewpassword(), request.getConfirmpassword());
            return "Password updated successfully!";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (RuntimeException e) {
            return "Failed: " + e.getMessage();
        }
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return jwtUtil.generateToken(existingUser.getUsername());
        }
        return "Invalid credentials!";
    }
}
