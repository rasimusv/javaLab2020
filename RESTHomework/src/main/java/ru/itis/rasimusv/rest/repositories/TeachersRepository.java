package ru.itis.rasimusv.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rasimusv.rest.models.Teacher;

import java.util.List;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findAllByIsDeletedIsNull();
}
