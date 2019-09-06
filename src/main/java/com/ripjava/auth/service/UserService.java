package com.ripjava.auth.service;

import com.ripjava.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
