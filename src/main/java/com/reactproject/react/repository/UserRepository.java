package com.reactproject.react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reactproject.react.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
