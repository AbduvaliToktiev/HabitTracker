package com.example.habittracker.controllers;

import com.example.habittracker.models.dtos.UserDto;
import com.example.habittracker.models.entities.User;
import com.example.habittracker.security.jwt.JwtTokenProvider;
import com.example.habittracker.security.passwordDto.ChangePassDto;
import com.example.habittracker.security.passwordDto.ResetPassDto;
import com.example.habittracker.security.securityDto.AuthenticationRequestDto;
import com.example.habittracker.security.securityDto.AuthenticationResponseDto;
import com.example.habittracker.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "1. Авторизация/Регистрация")
@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @ApiOperation("Авторизация")
    @PostMapping("/authentication")
    public ResponseEntity<?> authentication(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            AuthenticationResponseDto responseDto = new AuthenticationResponseDto();
            responseDto.setUsername(username);
            responseDto.setToken(token);

            return ResponseEntity.ok(responseDto);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.NOT_FOUND);

        }
    }

    @ApiOperation("Регистрация")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(toString(userService.register(userDto)));
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid save User: " + userDto.getUsername() + " " + e.getMessage());
        }
    }

    @ApiOperation("Сброс пароля пользователя")
    @PostMapping("/reset-password")
    ResponseEntity<?> resetPass(@RequestBody ResetPassDto resetPassDto) {
        try{
            User user = userService.findByEmail(resetPassDto.getEmail());
            if (user == null) {
                throw new NullPointerException("The email address '" + resetPassDto.getEmail() + "' is invalid");
            }
            String token = jwtTokenProvider.createResetToken(user.getUsername(), user.getRoles());
            resetPassDto.setUsername(user.getUsername());
            resetPassDto.setResetToken(token);
            userService.resetPassword(resetPassDto);
            return ResponseEntity.ok("Message sent successfully....");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error sending email", HttpStatus.CONFLICT);
        }
    }

    @ApiOperation("Создать новый пароль пользователя")
    @PostMapping("/change-password")
    ResponseEntity<?> changePassword(@RequestBody ChangePassDto changeDto) {
        if (!jwtTokenProvider.validateToken(changeDto.getToken())) {
            throw new RuntimeException("Token has expired");
        }
        if (!changeDto.getPassword().equals(changeDto.getPasswordConfirmation())) {
            throw new RuntimeException("passwords do not match");
        }
        try {
            userService.changePassword(changeDto);
            return ResponseEntity.ok("Password changed successfully....");
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid change password", HttpStatus.CONFLICT);
        }
    }



    private String toString(UserDto userDto) {
        return "Registration completed successfully " +
                "\n username: " + userDto.getUsername() +
                "\n email: " + userDto.getEmail();
    }


}
