package com.example.habittracker.repository;

import com.example.habittracker.models.entities.ResetPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPassRep extends JpaRepository<ResetPass, Long> {
}
