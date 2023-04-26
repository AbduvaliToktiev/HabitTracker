package com.example.habittracker.repository;

import com.example.habittracker.models.entities.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRep extends JpaRepository<Achievement,Long> {
}
