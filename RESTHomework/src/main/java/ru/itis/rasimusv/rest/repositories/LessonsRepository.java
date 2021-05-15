package ru.itis.rasimusv.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rasimusv.rest.models.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
