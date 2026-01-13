package com.example.demo.controller;

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
    public List<User> getAllUsers()
    {
        return userServices.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user)
    {
        return userServices.saveUser(user);
    }
}
