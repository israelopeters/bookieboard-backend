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
    public List<UserDto> getAllUsers() {
        return new ArrayList<>(userRepository
                        .findAll()
                        .stream()
                        .map(user -> dtoMapper.toUserDto(user))
                        .toList());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Set new bookieScore field to zero for users created before adding the field to the model
            if (user.get().getBookieScore() == null) {
                user = user.map(oldUser -> {
                        oldUser.setBookieScore(0);
                        return userRepository.save(oldUser);}
                );
            }
            return dtoMapper.toUserDto(
                    user.get());
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

    // Only call when creating admin user
    @Override
    public User addAdminUser(User user) {

        if (isUserPresent(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        user.setBookieScore(0);
        user.setBookieRank(User.UserRank.SEASONED);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = assignRoleAdmin();
        }
        user.setRoles(List.of(role));
        user.setDateCreated(LocalDate.now());

        return userRepository.save(user);
    }

    @Override
    public UserDto updateUserScore(String email, int newScore) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()) {
            existingUser = existingUser.map(user -> {
                user.setBookieScore(newScore);
                return userRepository.save(user);
            });
        } else {
            throw new UserNotFoundException("User not found!");
        }
        return dtoMapper.toUserDto(existingUser.get());
    }

    private Role assignRole() {
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

    private Role assignRoleAdmin() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    private boolean isUserPresent(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
