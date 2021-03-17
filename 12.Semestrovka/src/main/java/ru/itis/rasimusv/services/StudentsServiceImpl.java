package ru.itis.rasimusv.services;

import org.springframework.stereotype.Service;
import ru.itis.rasimusv.dto.StudentDto;
import ru.itis.rasimusv.repositories.StudentsRepository;

import java.util.List;

import static ru.itis.rasimusv.dto.StudentDto.from;

@Service
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentsServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return from(studentsRepository.findAll());
    }

}
