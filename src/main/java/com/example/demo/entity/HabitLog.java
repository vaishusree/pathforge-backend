package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="habit_log")
public class HabitLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="habit_id",nullable=false)
    private Habit habit;
    private LocalDate progressDate;
    private boolean status;
    public HabitLog() {
    }

    public HabitLog(Habit habit, LocalDate progressDate) {

        this.habit = habit;
        this.progressDate = progressDate;
        this.status=false;
    }

    public Long getId() {
        return id;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public LocalDate getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(LocalDate progressDate) {
        this.progressDate = progressDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
