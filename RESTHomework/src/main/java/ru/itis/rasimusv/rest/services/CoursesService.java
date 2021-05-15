package ru.itis.rasimusv.rest.services;

import ru.itis.rasimusv.rest.dto.CourseDto;
import ru.itis.rasimusv.rest.models.Course;
import ru.itis.rasimusv.rest.dto.TeacherDto;

import java.util.List;

public interface CoursesService {
    List<Course> getAllCourses();

    Course addCourse(CourseDto course);

    Course addTeacherIntoCourse(Long courseId, TeacherDto teacher);
}
