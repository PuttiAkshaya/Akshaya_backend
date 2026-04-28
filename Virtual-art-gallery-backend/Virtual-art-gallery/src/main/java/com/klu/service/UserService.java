package com.klu.service;

import com.klu.model.User;

import java.util.List;

public interface UserService {

    // ✅ Create user
    User createUser(User user);

    // ✅ Get all users
    List<User> getAllUsers();

    // ✅ Get user by ID
    User getUserById(Long id);

    // ✅ Delete user
    void deleteUser(Long id);
}