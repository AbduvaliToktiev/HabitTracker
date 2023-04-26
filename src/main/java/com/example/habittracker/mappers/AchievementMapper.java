package com.example.habittracker.mappers;

import com.example.habittracker.models.dtos.AchievementDto;
import com.example.habittracker.models.entities.Achievement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AchievementMapper extends BaseMapper<Achievement, AchievementDto>{
    AchievementMapper INSTANCE = Mappers.getMapper(AchievementMapper.class);
}
