package com.klu.controller;

import com.klu.dto.AuthRequest;
import com.klu.dto.AuthResponse;
import com.klu.model.User;
import com.klu.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ REGISTER
    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {

        String msg = authService.register(user);

        AuthResponse res = new AuthResponse();
        res.setMessage(msg);

        return res;
    }

    @Autowired
    private com.klu.repository.UserRepository userRepository;

    // ✅ LOGIN → RETURN TOKEN
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        String token = authService.login(request.getEmail(), request.getPassword());
        
        com.klu.model.User user = userRepository.findByEmail(request.getEmail()).get();

        AuthResponse res = new AuthResponse();
        res.setMessage(token);
        res.setUserId(user.getId());
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());

        return res;
    }
}