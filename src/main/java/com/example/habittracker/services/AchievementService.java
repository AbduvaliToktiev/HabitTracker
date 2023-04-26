package com.example.habittracker.services;

import com.example.habittracker.models.dtos.AchievementDto;

import java.util.List;

public interface AchievementService {

    AchievementDto save (Long profileId, Long habitId);
    AchievementDto findById(Long id);
    List<AchievementDto> findAll();
    AchievementDto delete (Long id);

}
