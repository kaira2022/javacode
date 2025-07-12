package com.reactproject.react.service;

import com.reactproject.react.model.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService{
    public User saveUser(User user) ;
    public User findByUsername(String username) ;    
    User resetPassword(String email, String newPassword, String confirmPassword);

}
