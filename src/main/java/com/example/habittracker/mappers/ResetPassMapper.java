package com.example.habittracker.mappers;

import com.example.habittracker.models.entities.ResetPass;
import com.example.habittracker.security.passwordDto.ResetPassDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResetPassMapper extends BaseMapper<ResetPass, ResetPassDto> {
    ResetPassMapper INSTANCE = Mappers.getMapper(ResetPassMapper.class);
}
