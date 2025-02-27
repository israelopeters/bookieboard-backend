package com.example.bookieboard_backend.model.dto;

import com.example.bookieboard_backend.model.Role;
import com.example.bookieboard_backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private String email;

    private String firstName;

    private String lastName;

    private User.UserRank bookieRank;

    private List<Role> roles;
}
