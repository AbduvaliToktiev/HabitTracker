package com.example.habittracker.security.securityDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponseDto {
    String username;
    String token;
}
