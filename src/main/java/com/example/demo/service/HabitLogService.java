package com.example.demo.service;

import com.example.demo.entity.Habit;
import com.example.demo.entity.HabitLog;
import com.example.demo.repository.HabitLogRepository;
import com.example.demo.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;

    public HabitLogService(HabitLogRepository habitLogRepository, HabitRepository habitRepository) {
        this.habitLogRepository = habitLogRepository;
        this.habitRepository = habitRepository;
    }


    public HabitLog checkIn(Long habitId, LocalDate progressDate, boolean status)
    {
        if(habitId == null) throw new IllegalArgumentException("Habit Id cannot be null");
        if(progressDate==null) progressDate=LocalDate.now();
        Habit habit=habitRepository.findById(habitId).orElseThrow(()->new IllegalArgumentException("Habit not found"));
        if(progressDate.isBefore(habit.getStartDate())) throw new IllegalArgumentException("Progress date cannot be before habit date");
        if(progressDate.isAfter(LocalDate.now()))  throw new IllegalArgumentException("Progress date cannot be in future ");

        HabitLog habitLog=habitLogRepository.findByHabitAndProgressDate(habit,progressDate).orElseGet(()->new HabitLog(habit,progressDate));
        habitLog.setStatus(status);

        return habitLogRepository.save(habitLog);
    }
}
