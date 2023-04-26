package com.example.habittracker.mappers;

import com.example.habittracker.models.dtos.UserDto;
import com.example.habittracker.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto>{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
