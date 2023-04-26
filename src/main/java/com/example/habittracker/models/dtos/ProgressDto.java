package com.example.habittracker.models.dtos;

import com.example.habittracker.models.entities.Habit;
import com.example.habittracker.models.entities.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProgressDto extends BaseDto{

    Profile profile;
    Habit habit;
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date progressDate;
    int target;
}
