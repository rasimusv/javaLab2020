package ru.itis.rasimusv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rasimusv.models.Student;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
