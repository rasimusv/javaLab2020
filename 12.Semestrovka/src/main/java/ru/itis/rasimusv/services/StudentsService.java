package ru.itis.rasimusv.services;

import ru.itis.rasimusv.dto.StudentDto;

import java.util.List;

public interface StudentsService {
    List<StudentDto> getAllStudents();
}
