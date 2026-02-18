package com.example.demo.repository;

import com.example.demo.entity.Habit;
import com.example.demo.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitLogRepository extends JpaRepository<HabitLog,Long> {
    //List<HabitLog> findByHabitAndDateBetween(Habit habit, LocalDate FromDate, LocalDate ToDate);

    List<HabitLog> findByHabitAndProgressDateBetween(Habit habit, LocalDate finalFromDate, LocalDate finalToDate);
    Optional<HabitLog> findByHabitAndProgressDate(Habit habit, LocalDate finalProgressDate);
}
