package com.klu.service.impl;

import com.klu.model.User;
import com.klu.repository.UserRepository;
import com.klu.service.AuthService;
import com.klu.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ REGISTER
    @Override
    public String register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        userRepository.save(user);

        return "User registered successfully";
    }

    // ✅ LOGIN → RETURN TOKEN
    @Override
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        // 🔥 generate JWT token
        return jwtUtil.generateToken(email);
    }
}