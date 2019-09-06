package com.ripjava.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ripjava.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
