package com.example.demo.service;

import com.example.demo.dto.HabitRequest;
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

    public Habit createHabit(Long userId,HabitRequest habit)
    {
        if(userId==null) throw new IllegalArgumentException("User id cannot be null");
        if(habit==null) throw new IllegalArgumentException("Habit cannot be null");
        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));
        long count=habitRepository.countByUser(user);
        if(count>=10)
        {
            throw new IllegalStateException("Users cannot have more than 10 habits");
        }
        if(habit.getHabitName()==null) throw new IllegalArgumentException("Habit Name cannot be null");
        if((habit.getHabitName()!=null) && habitRepository.existsByUserAndHabitName(user,habit.getHabitName()))
        {
            throw new IllegalStateException("Habit already exists for the user");
        }
        if(habit.getStartDate()!=null && habit.getStartDate().isAfter(LocalDate.now()))// method in entitiy bcz it has ntg to do with database
        {
            throw new IllegalArgumentException("Start date cannot be in future");
        }/*
        habit.setUser(user);
        return habitRepository.save(habit);
        here now we are creating a new habit entity because previously we were taking habit
        directly from the client which basically means we are exposing our entire architecture.
        we are practically saying client to construct the database object,
        OVERPOSTING VULNERABILITY: CLIENT SENDS FIELD WE DIDNT INTEND TO ALLOW

        */
        Habit newHabit=new Habit(
                habit.getHabitName(),
                user,
                habit.getStartDate(),
                habit.getFrequency()
        );
        //here this entity is trusted bcz it is fetched from db
        /*You constructed it in service
            You validated all inputs
            You fetched user from DB
            You did ownership check*/
        return habitRepository.save(newHabit);
    }
    public void deleteHabit(Long userId,Long habitId)
    {
        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));
        Habit habit=habitRepository.findById(habitId).orElseThrow(()->new IllegalArgumentException("Habit not found"));
        if(!habit.getUser().getId().equals(user.getId()))
        {
            throw new IllegalStateException("Cannot delete habit of another user");
        }
        habitRepository.delete(habit);
    }
    public List<Habit> getHabitsByUser(Long userId)
    {
        User user=userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User not found"));
        return habitRepository.findByUser(user);
    }

    public Habit updateHabit(Long userId, Long habitId, HabitRequest updateHabit) {
        if(updateHabit==null) throw new IllegalArgumentException("Update data cannot be null");
        Habit existingHabit=habitRepository.findById(habitId).orElseThrow(()->new IllegalArgumentException("Habit not found"));
        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));
        if(!existingHabit.getUser().getId().equals(user.getId()))
        {
            throw new IllegalStateException("Cannot update habit of other user");
        }
        if(updateHabit.getFrequency()!=null) existingHabit.setFrequency(updateHabit.getFrequency());
        if(updateHabit.getHabitName()!=null) existingHabit.setHabitName(updateHabit.getHabitName());
        if(updateHabit.getStartDate()!=null )
        {
            if(updateHabit.getStartDate().isAfter(LocalDate.now()))
            {
                throw new IllegalArgumentException("Start date cannot be in future");
            }
            else
            existingHabit.setStartDate(updateHabit.getStartDate());
        }
        return habitRepository.save(existingHabit);

    }
}
