package com.example.habittracker.models.dtos;

import com.example.habittracker.models.enums.Frequency;
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
public class HabitDto extends BaseDto{

    String name;
    String description;
    int target;
    Frequency frequency;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date endDate;
}
