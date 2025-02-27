package com.example.bookieboard_backend.model;

import com.example.bookieboard_backend.model.dto.UserCreationDto;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    public User toUser(UserCreationDto userCreationDto) {
        User user = new User();
        user.setFirstName(userCreationDto.getFirstName());
        user.setLastName(userCreationDto.getLastName());
        user.setEmail(userCreationDto.getEmail());
        user.setPassword(userCreationDto.getPassword());
        return user;
    }
}
