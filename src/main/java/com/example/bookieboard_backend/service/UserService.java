package com.example.bookieboard_backend.service;

import com.example.bookieboard_backend.model.User;
import com.example.bookieboard_backend.model.dto.UserCreationDto;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByEmail(String email);
    User addUser(UserCreationDto userCreationDto);
}
