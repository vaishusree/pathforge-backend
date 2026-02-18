package com.example.demo.service;

import com.example.demo.entity.Habit;
import com.example.demo.entity.HabitLog;
import com.example.demo.entity.User;
import com.example.demo.repository.HabitLogRepository;
import com.example.demo.repository.HabitRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

//Service cannot ignore userId, if it is then our url(RequestMapping) is lying
@Service
public class HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitLogService(HabitLogRepository habitLogRepository, HabitRepository habitRepository, UserRepository userRepository) {
        this.habitLogRepository = habitLogRepository;
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }


    public HabitLog checkIn(Long habitId,Long userId, LocalDate progressDate, boolean status)
    {
        if(userId==null) throw new IllegalArgumentException("User id cannot be null");
        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));
        if(habitId == null) throw new IllegalArgumentException("Habit Id cannot be null");
        LocalDate finalProgressDate=(progressDate==null) ?LocalDate.now():progressDate;
        Habit habit=habitRepository.findById(habitId).orElseThrow(()->new IllegalArgumentException("Habit not found"));
        if(!habit.getUser().getId().equals(user.getId())) throw new IllegalStateException("Habit Log doesn't belong to the User");
        if(finalProgressDate.isBefore(habit.getStartDate())) throw new IllegalArgumentException("Progress date cannot be before habit date");
        if(finalProgressDate.isAfter(LocalDate.now()))  throw new IllegalArgumentException("Progress date cannot be in future ");
        HabitLog habitLog=habitLogRepository.findByHabitAndProgressDate(habit,finalProgressDate).orElseGet(()->new HabitLog(habit, finalProgressDate));
        //variables inside lambda must be final or effectively final
        //effectively final-once the variable is modified it can nvr be modified again
        //hence in thr we created a variable finalProgressDate which sets the progressDate to localDate if null
        //here we are using ternary operator bcz we only assigned it once and nvr changed it.but in case of if else then later wea re updating progressDate which is against lambda rules
        habitLog.setStatus(status);

        return habitLogRepository.save(habitLog);
    }

    public List<HabitLog> getLogsForHabit(Long habitId,Long userId, LocalDate fromDate, LocalDate toDate)
    {

        if(userId==null) throw new IllegalArgumentException("User id cannot be null");
        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));

        if(habitId==null) throw new IllegalArgumentException("Habit Id cannot be null");
        Habit habit=habitRepository.findById(habitId).orElseThrow(()->new IllegalArgumentException("Habit is not found"));

        if(!habit.getUser().getId().equals(user.getId())) throw new IllegalStateException("Habit Log doesn't belong to the User");
        LocalDate finalFromDate=(fromDate==null)?habit.getStartDate():fromDate;
        LocalDate finalToDate=(toDate==null)?LocalDate.now():toDate;

        if(finalFromDate.isBefore(habit.getStartDate())) throw new IllegalArgumentException("From date cannot be before start date");
        if(finalToDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("To date cannot be in future");

        List<HabitLog> habitLog=habitLogRepository.findByHabitAndProgressDateBetween(habit,finalFromDate,finalToDate);

        return habitLog;

    }
}
