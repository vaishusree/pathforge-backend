package com.example.demo.repository;

import com.example.demo.entity.Habit;
import com.example.demo.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HabitLogRepository extends JpaRepository<HabitLog,Long> {
    Optional<HabitLog>  findByHabitAndProgressDate(Habit habit, LocalDate progressDate);
}
