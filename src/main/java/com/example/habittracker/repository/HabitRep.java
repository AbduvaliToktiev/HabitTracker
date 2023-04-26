package com.example.habittracker.repository;

import com.example.habittracker.models.entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRep extends JpaRepository<Habit, Long> {
}
