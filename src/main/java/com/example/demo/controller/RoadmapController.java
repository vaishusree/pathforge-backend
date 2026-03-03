package com.example.demo.controller;

import com.example.demo.dto.RoadmapRequest;
import com.example.demo.entity.Roadmap;
import com.example.demo.service.RoadmapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping
    public List<Roadmap> getHabitsByUser(@PathVariable Long userId)
    {
        return roadmapService.getHabitsByUser(userId);
    }

    @PostMapping
    //anything that comes from request body is untrusted
    public Habit createHabit(@PathVariable Long userId,@RequestBody HabitRequest habit )
    {
        return habitService.createHabit(userId,habit);
    }

    @PutMapping("/{habitId}")
    public Habit updateHabit(@PathVariable Long userId, @PathVariable Long habitId,@RequestBody HabitRequest req)
    {
        return habitService.updateHabit(userId,habitId,req);
    }
    @DeleteMapping("/{habitId}")
    public void deleteHabit(@PathVariable Long userId,@PathVariable Long habitId)
    {
        habitService.deleteHabit(userId,habitId);
    }



}
