package com.example.habittracker.mappers;

import com.example.habittracker.models.dtos.ProgressDto;
import com.example.habittracker.models.entities.Progress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgressMapper extends BaseMapper<Progress, ProgressDto>{
    ProgressMapper INSTANCE = Mappers.getMapper(ProgressMapper.class);
}
