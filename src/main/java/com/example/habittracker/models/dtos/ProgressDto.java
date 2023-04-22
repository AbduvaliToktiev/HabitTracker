package com.example.habittracker.models.dtos;

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

    Long profileId;
    Long habitId;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date progressDate;
    int target;
}
