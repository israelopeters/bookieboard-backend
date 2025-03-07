package com.example.bookieboard_backend.service;

import com.example.bookieboard_backend.model.Role;
import com.example.bookieboard_backend.model.User;
import com.example.bookieboard_backend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userCheck = userRepository.findByEmail(email);

        if (userCheck.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password!");
        } else {
            User user = userCheck.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles())
            );
        }
    }

    public static Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        // TODO: Based on Lines 43 and 46, refactor roles class name field to follow Spring Security conventions
    }
}
