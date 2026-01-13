package com.example.demo.controller;

import com.example.demo.entity.Habit;
import com.example.demo.service.HabitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public List<Habit> getHabits()
    {
        return habitService.getHabits();
    }

    @PostMapping
    //anything that comes from request body is untrusted
    public Habit createHabit(@PathVariable Long userId,@RequestBody Habit habit )
    {
        return habitService.createHabit(userId,habit);
    }

    @DeleteMapping

    public void deleteHabit(@PathVariable Long userId,@RequestBody Habit habit)
    {
        habitService.deleteHabit(userId,habit);
    }


}
