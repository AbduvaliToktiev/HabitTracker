package com.example.habittracker.mappers;

import com.example.habittracker.models.dtos.ProfileDto;
import com.example.habittracker.models.entities.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper extends BaseMapper<Profile, ProfileDto>{
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);
}
