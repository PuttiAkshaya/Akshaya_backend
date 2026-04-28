package com.klu.service.impl;

import com.klu.model.User;
import com.klu.repository.UserRepository;
import com.klu.service.UserService;
import com.klu.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ CREATE USER
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ✅ GET ALL USERS
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ GET USER BY ID
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // ✅ DELETE USER
    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id); // reuse method
        userRepository.delete(user);
    }
}