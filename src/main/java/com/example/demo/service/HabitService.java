package com.example.demo.service;

import com.example.demo.entity.Habit;
import com.example.demo.entity.User;
import com.example.demo.repository.HabitRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


import java.util.List;
//always add basic null checks
//then add validation checks
//business logic

//SERVER MUST ASSUME INPUTS ARE UNTRUSTED

@Service
public class HabitService {
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
    //with final the ref object cannot point to a different obj
    //u cannot assign the same ref to diff object


    public HabitService(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    public Habit createHabit(Long userId,Habit habit)
    {
        if(userId==null) throw new IllegalArgumentException("User id cannot be null");
        if(habit==null) throw new IllegalArgumentException("Habit cannot be null");
        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));
        long count=habitRepository.countByUser(user);
        if(count>=10)
        {
            throw new IllegalStateException("Users cannot have more than 10 habits");
        }
        if((habit.getHabitName()!=null) && habitRepository.existsByUserAndHabitName(user,habit.getHabitName()))
        {
            throw new IllegalStateException("Habit already exists for the user");
        }
        if(habit.getStartDate()!=null && habit.getStartDate().isAfter(LocalDate.now()))// method in entitiy bcz it has ntg to do with database
        {
            throw new IllegalArgumentException("Start date cannot be in future");
        }
        habit.setUser(user);
        return habitRepository.save(habit);
    }
    public void deleteHabit(Long userId,Habit habit)
    {
        if(habit==null) throw new IllegalArgumentException("Habit is null");
        if(userId==null) throw new IllegalArgumentException("User id is null");
        habitRepository.delete(habit);
    }
    public List<Habit> getHabits()
    {
        return habitRepository.findAll();
    }
}
