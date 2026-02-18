package com.example.demo.controller;

import com.example.demo.entity.HabitLog;
import com.example.demo.service.HabitLogService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/habits/{habitId}/logs")
public class HabitLogController {
    private final HabitLogService habitLogService;

    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    @GetMapping
    public List<HabitLog> getHabitLog(@PathVariable Long habitId,@RequestParam(required=false) LocalDate fromDate,@RequestParam(required=false) LocalDate toDate)
    {// required=false bcz service also handles null
        return habitLogService.getLogsForHabit(habitId, fromDate, toDate);
    }

    @PostMapping
    public HabitLog createHabitLog(@PathVariable Long habitId,@RequestParam LocalDate progressDate,@RequestBody boolean status )
    {
        return habitLogService.checkIn(habitId, progressDate,status);
    }
}
