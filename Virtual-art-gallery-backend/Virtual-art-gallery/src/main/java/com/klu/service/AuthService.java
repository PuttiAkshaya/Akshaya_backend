package com.klu.service;

import com.klu.model.User;

public interface AuthService {

    // register
    String register(User user);

    // login → returns JWT token
    String login(String email, String password);
}