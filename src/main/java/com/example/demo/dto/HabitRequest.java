package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class HabitRequest {
    @NotBlank(message = "Habit name cannot be empty")
    private String habitName;
    private String frequency;
    private LocalDate startDate;

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
