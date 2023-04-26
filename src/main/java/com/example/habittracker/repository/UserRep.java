package com.example.habittracker.repository;

import com.example.habittracker.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<User, Long> {

    User findByUsername(String name);
    User findByEmail(String email);
}
