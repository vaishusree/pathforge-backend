package com.example.demo.controller;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import java.util.List;
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userServices;

    public UserController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<UserResponse> getAllUsers()
    {
        return userServices.getAllUsers();
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest user)
    {
        return userServices.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userServices.deleteUser(userId);
    }
}
