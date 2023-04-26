package com.example.habittracker.controllers;

import com.example.habittracker.models.dtos.AchievementDto;
import com.example.habittracker.services.AchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "5. Достижения")
@RestController
@RequestMapping(value = "/api/v1/achievement")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestParam Long profileId, @RequestParam Long habitId) {
        try {
            return new ResponseEntity<>(achService.save(profileId, habitId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/find/by/id")
    @ApiOperation("Поиск достижения по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(achService.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/all")
    @ApiOperation("Вывод всех привычек")
    ResponseEntity<List<AchievementDto>> findAll() {
        return ResponseEntity.ok(achService.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаления достижения")
    ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            achService.delete(id);
            return ResponseEntity.ok("database entry deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
