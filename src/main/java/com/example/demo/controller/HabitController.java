package com.example.demo.controller;
import com.example.demo.dto.HabitRequest;
import com.example.demo.dto.HabitResponse;
import com.example.demo.service.HabitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/users/{userId}/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public List<HabitResponse> getHabitsByUser(@PathVariable Long userId)
    {
        return habitService.getHabitsByUser(userId);
    }

    @PostMapping
    //anything that comes from request body is untrusted
    public HabitResponse createHabit(@PathVariable Long userId, @RequestBody HabitRequest habit )
    {
        return habitService.createHabit(userId,habit);
    }

    @PutMapping("/{habitId}")
    public HabitResponse updateHabit(@PathVariable Long userId, @PathVariable Long habitId,@RequestBody HabitRequest req)
    {
        return habitService.updateHabit(userId,habitId,req);
    }
    @DeleteMapping("/{habitId}")
    public void deleteHabit(@PathVariable Long userId,@PathVariable Long habitId)
    {
        habitService.deleteHabit(userId,habitId);
    }




}
