package com.example.demo.repository;

import com.example.demo.entity.Habit;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit,Long> {
    long countByUser(User user);
    boolean existsByUserAndHabitName(User user, String habitName);

    List<Habit> findByUser(User user);
}
