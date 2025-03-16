package com.example.bookieboard_backend.controller;

import com.example.bookieboard_backend.model.dto.UserCreationDto;
import com.example.bookieboard_backend.model.dto.UserDto;
import com.example.bookieboard_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserCreationDto userCreationDto) {
        return new ResponseEntity<>(
                userService.addUser(userCreationDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserDto> updateUserScore(@RequestBody HashMap<String, Object> newFieldValues) {
        return new ResponseEntity<>(
                userService.updateUserScore(newFieldValues), HttpStatus.ACCEPTED);
    }

}
