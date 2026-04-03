package com.example.demo.dto;

import java.time.LocalDate;

public class HabitResponse {

    private final Long id;
    private final String habitName;
    private final Long userId;   // NOT full User
    private final LocalDate startDate;
    private final String frequency;

    public HabitResponse(Long id, String habitName, Long userId, LocalDate startDate, String frequency) {
        this.id = id;
        this.habitName = habitName;
        this.userId = userId;
        this.startDate = startDate;
        this.frequency = frequency;
    }


    public Long getId() {
        return id;
    }

    public String getHabitName() {
        return habitName;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getFrequency() {
        return frequency;
    }

    // getters
}
