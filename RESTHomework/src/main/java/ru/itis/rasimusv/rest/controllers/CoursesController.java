package ru.itis.rasimusv.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.rasimusv.rest.dto.CourseDto;
import ru.itis.rasimusv.rest.models.Course;
import ru.itis.rasimusv.rest.services.CoursesService;
import ru.itis.rasimusv.rest.dto.TeacherDto;

import java.util.List;

@RestController
public class CoursesController {

    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(coursesService.getAllCourses());
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDto course) {
        return ResponseEntity.ok(coursesService.addCourse(course));
    }

    @PostMapping("/courses/{course-id}/teachers")
    public ResponseEntity<Course> addTeacherIntoCourse(@PathVariable("course-id") Long courseId,
                                                       @RequestBody TeacherDto teacher) {
        return ResponseEntity.ok(coursesService.addTeacherIntoCourse(courseId, teacher));
    }
}
