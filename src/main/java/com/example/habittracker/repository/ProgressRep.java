package com.example.habittracker.repository;

import com.example.habittracker.models.entities.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRep extends JpaRepository<Progress, Long> {
}
