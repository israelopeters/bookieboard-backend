package com.example.bookieboard_backend.repository;

import com.example.bookieboard_backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
