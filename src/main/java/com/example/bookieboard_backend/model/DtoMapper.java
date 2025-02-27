package com.example.bookieboard_backend.model;

import com.example.bookieboard_backend.model.dto.UserCreationDto;
import com.example.bookieboard_backend.model.dto.UserDto;
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

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setBookieRank(user.getBookieRank());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
