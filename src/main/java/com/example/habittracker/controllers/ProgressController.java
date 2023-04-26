package com.example.habittracker.controllers;

import com.example.habittracker.models.dtos.ProgressDto;
import com.example.habittracker.services.ProgressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "4. Прогресс соблюдения привычек")
@Controller
@RequestMapping(value = "/api/v1/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody ProgressDto progressDto) {
        try {
            return new ResponseEntity<>(progressService.save(progressDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Поиск профиля по id")
    ResponseEntity<?> findByName(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(progressService.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод всех прогрессов")
    ResponseEntity<List<ProgressDto>> findAll() {
        return ResponseEntity.ok(progressService.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаления прогресса")
    ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            progressService.delete(id);
            return ResponseEntity.ok("database entry deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
