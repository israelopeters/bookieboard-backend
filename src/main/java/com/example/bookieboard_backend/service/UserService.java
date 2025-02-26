package com.example.bookieboard_backend.service;

import com.example.bookieboard_backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserByEmail(String email);
}
