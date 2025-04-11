package com.example.bookieboard_backend.service;

import com.example.bookieboard_backend.model.User;
import com.example.bookieboard_backend.model.dto.UserCreationDto;
import com.example.bookieboard_backend.model.dto.UserDto;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserByEmail(String email);
    UserDto addUser(UserCreationDto userCreationDto);
    User addAdminUser(User user);
    UserDto updateUserScore(HashMap<String, Object> newFieldValues);
    void deleteUserById(User userToDelete);
}
