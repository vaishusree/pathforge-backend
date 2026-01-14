package com.example.demo.controller;

import com.example.demo.entity.Habit;
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
    public List<Habit> getHabitsByUser(@PathVariable Long userId)
    {
        return habitService.getHabitsByUser(userId);
    }

    @PostMapping
    //anything that comes from request body is untrusted
    public Habit createHabit(@PathVariable Long userId,@RequestBody Habit habit )
    {
        return habitService.createHabit(userId,habit);
    }

    @PutMapping("/{habitId}")
    public Habit updateHabit(@PathVariable Long userId, @PathVariable Long habitId,@RequestBody Habit updateHabit)
    {
        return habitService.updateHabit(userId,habitId,updateHabit);
    }
    @DeleteMapping("/{habitId}")
    public void deleteHabit(@PathVariable Long userId,@PathVariable Long habitId)
    {
        habitService.deleteHabit(userId,habitId);
    }




}
