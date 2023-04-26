package com.example.habittracker.models.dtos;

import com.example.habittracker.models.entities.Role;
import com.example.habittracker.models.enums.Gender;
import com.example.habittracker.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto extends BaseDto{
    String username;
    String email;
    String password;
    Gender gender;
    int age;
    List<Role> roles;
}
