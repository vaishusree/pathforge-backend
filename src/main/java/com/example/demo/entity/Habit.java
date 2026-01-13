package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "habits")
public class Habit
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // entity should be saved first, before save id is null bcz db allots it automatically
    private Long Id;
    private String habitName;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    private LocalDate startDate;
    private String frequency;



    public Habit() {
    }

    public Habit(String habitName, User user, LocalDate startDate, String frequency) {
        this.habitName = habitName;
        this.user = user;
        this.startDate = startDate;
        this.frequency = frequency;
    }

    public Long getId() {
        return Id;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
