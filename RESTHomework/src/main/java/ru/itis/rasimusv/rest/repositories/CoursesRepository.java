package ru.itis.rasimusv.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rasimusv.rest.models.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {
}
