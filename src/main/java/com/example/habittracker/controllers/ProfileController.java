package com.example.habittracker.controllers;

import com.example.habittracker.models.dtos.ProfileDto;
import com.example.habittracker.services.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "2. Профиль пользователя")
@RestController
@RequestMapping(value = "/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody ProfileDto profileDto) {
        try {
            return new ResponseEntity<>(profileService.save(profileDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/find/by/id")
    @ApiOperation("Поиск профиля по id")
    ResponseEntity<?> findByName(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(profileService.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/all")
    @ApiOperation("Вывод всех профилей")
    ResponseEntity<List<ProfileDto>> findAll() {
        return ResponseEntity.ok(profileService.findAll());
    }

    @PutMapping("/update")
    @ApiOperation("Обновить профиль")
    ResponseEntity<?> update(@RequestBody ProfileDto profileDto){
        try {
            return new ResponseEntity<>(profileService.update(profileDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("/delete")
    @ApiOperation("Удаления профиля")
    ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            profileService.delete(id);
            return ResponseEntity.ok("database entry deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
