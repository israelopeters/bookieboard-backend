package com.example.bookieboard_backend.controller;

import com.example.bookieboard_backend.model.dto.UserDto;
import com.example.bookieboard_backend.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;

    @Autowired
    MockMvc mockMvcController;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(userController).build();
        ObjectMapper objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET / returns empty list and the OK status code")
    void getAllUsersWhenNoUserExists() throws Exception {
        // Arrange
        List<UserDto> userDtoList = List.of();
        when(userServiceImpl.getAllUsers()).thenReturn(userDtoList);

        // Act and Assert
        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/users/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void addUser() {
    }

    @Test
    void updateUserScore() {
    }
}