package ru.itis.rasimusv.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.rasimusv.rest.dto.TeacherDto;
import ru.itis.rasimusv.rest.services.TeachersService;

import java.util.List;

@RestController
public class TeachersController {

    private final TeachersService teachersService;

    public TeachersController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        return ResponseEntity.ok(teachersService.getAllTeachers());
    }

    @ApiOperation(value = "Добавление педагога")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = TeacherDto.class)})
    @PostMapping("/teachers")
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(teachersService.addTeacher(teacher));
    }

    @PutMapping("/teachers/{teacher-id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable("teacher-id") Long teacherId, @RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(teachersService.updateTeacher(teacherId, teacher));
    }

    @DeleteMapping("/teachers/{teacher-id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("teacher-id") Long teacherId) {
        teachersService.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }
}
