package com.example.habittracker.repository;

import com.example.habittracker.models.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRep extends JpaRepository<Profile, Long> {
}
