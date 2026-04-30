package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
    public List<UserResponse> getAllUsers() {

        List<User> users= userRepository.findAll();
        return users.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponse saveUser(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new IllegalStateException("Email already exists");
        }
        String password = request.getPassword();

        if(password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if(password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        if(!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }

        if(!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least one number");
        }

        if(!password.matches(".*[!@#$%^&*()].*")) {
            throw new IllegalArgumentException("Password must contain at least one special character");
        }

        if(request.getEmail() == null || !request.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        User savedUser= userRepository.save(user);

        return mapToResponse(savedUser);
    }
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(user);
    }

}
