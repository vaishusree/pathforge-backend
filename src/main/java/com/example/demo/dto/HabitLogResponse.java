package com.example.demo.dto;

import java.time.LocalDate;

public class HabitLogResponse {
    private final Long id;
    private final Long habitId;
    private final LocalDate progressDate;
    private final boolean status;

    public HabitLogResponse(Long id, Long habitId, LocalDate progressDate, boolean status) {
        this.id = id;
        this.habitId = habitId;
        this.progressDate = progressDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getHabitId() {
        return habitId;
    }

    public LocalDate getProgressDate() {
        return progressDate;
    }

    public boolean isStatus() {
        return status;
    }
}
