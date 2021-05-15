package ru.itis.rasimusv.rest.services;

import org.springframework.stereotype.Service;
import ru.itis.rasimusv.rest.dto.CourseDto;
import ru.itis.rasimusv.rest.dto.TeacherDto;
import ru.itis.rasimusv.rest.models.Course;
import ru.itis.rasimusv.rest.models.Teacher;
import ru.itis.rasimusv.rest.repositories.CoursesRepository;
import ru.itis.rasimusv.rest.repositories.TeachersRepository;

import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;
    private final TeachersRepository teachersRepository;

    public CoursesServiceImpl(CoursesRepository coursesRepository, TeachersRepository teachersRepository) {
        this.coursesRepository = coursesRepository;
        this.teachersRepository = teachersRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Course addCourse(CourseDto course) {
        return coursesRepository.save(Course.builder()
                .title(course.getTitle())
                .build());
    }

    @Override
    public Course addTeacherIntoCourse(Long courseId, TeacherDto teacher) {
        Course course = coursesRepository.findById(courseId)
                .orElseThrow(IllegalArgumentException::new);
        Teacher teacherForCourse = teachersRepository.findById(teacher.getId())
                .orElseThrow(IllegalArgumentException::new);

        course.getTeachers().add(teacherForCourse);
        coursesRepository.save(course);
        return course;
    }
}
