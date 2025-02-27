package com.example.bookieboard_backend.service;

import com.example.bookieboard_backend.exception.UserAlreadyExistsException;
import com.example.bookieboard_backend.exception.UserNotFoundException;
import com.example.bookieboard_backend.model.DtoMapper;
import com.example.bookieboard_backend.model.Role;
import com.example.bookieboard_backend.model.User;
import com.example.bookieboard_backend.model.dto.UserCreationDto;
import com.example.bookieboard_backend.model.dto.UserDto;
import com.example.bookieboard_backend.repository.RoleRepository;
import com.example.bookieboard_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DtoMapper dtoMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found!");
        }
    }

    @Override
    public UserDto addUser(UserCreationDto userCreationDto) {

        if (isUserPresent(userCreationDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        User user = dtoMapper.toUser(userCreationDto);
        user.setBookieRank(User.UserRank.ROOKIE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = assignRole();
        }
        user.setRoles(List.of(role));
        user.setDateCreated(LocalDate.now());

        User savedUser = userRepository.save(user);
        return dtoMapper.toUserDto(savedUser);
    }

    private Role assignRole() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

    private boolean isUserPresent(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
