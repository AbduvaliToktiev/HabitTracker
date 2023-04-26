package com.example.habittracker.controllers;

import com.example.habittracker.models.dtos.HabitDto;
import com.example.habittracker.services.HabitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "3. Привычки")
@RestController
@RequestMapping(value = "/api/v1/habit")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody HabitDto habitDto) {
        try {
            return new ResponseEntity<>(habitService.save(habitDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/find/by/id")
    @ApiOperation("Поиск привычки по id")
    ResponseEntity<?> findByName(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(habitService.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find/all")
    @ApiOperation("Вывод всех привычек")
    ResponseEntity<List<HabitDto>> findAll() {
        return ResponseEntity.ok(habitService.findAll());
    }

    @PutMapping("/update")
    @ApiOperation("Обновить привычку")
    ResponseEntity<?> update(@RequestBody HabitDto habitDto){
        try {
            return new ResponseEntity<>(habitService.update(habitDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("/delete")
    @ApiOperation("Удаления профиля")
    ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            habitService.delete(id);
            return ResponseEntity.ok("database entry deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
