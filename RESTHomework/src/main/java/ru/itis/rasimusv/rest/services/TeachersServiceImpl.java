package ru.itis.rasimusv.rest.services;

import org.springframework.stereotype.Service;
import ru.itis.rasimusv.rest.models.Teacher;
import ru.itis.rasimusv.rest.dto.TeacherDto;
import ru.itis.rasimusv.rest.repositories.TeachersRepository;

import java.util.List;

import static ru.itis.rasimusv.rest.dto.TeacherDto.from;

@Service
public class TeachersServiceImpl implements TeachersService {

    private final TeachersRepository teachersRepository;

    public TeachersServiceImpl(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return from(teachersRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public TeacherDto addTeacher(TeacherDto teacher) {
        Teacher newTeacher = Teacher.builder()
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .build();

        teachersRepository.save(newTeacher);
        return from(newTeacher);
    }

    @Override
    public TeacherDto updateTeacher(Long teacherId, TeacherDto teacher) {
        Teacher teacherForUpdate = teachersRepository.findById(teacherId)
                .orElseThrow(IllegalArgumentException::new);
        teacherForUpdate.setFirstName(teacher.getFirstName());
        teacherForUpdate.setLastName(teacher.getLastName());
        teachersRepository.save(teacherForUpdate);
        return from(teacherForUpdate);
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        Teacher teacherForDelete = teachersRepository.findById(teacherId)
                .orElseThrow(IllegalArgumentException::new);
        teacherForDelete.setIsDeleted(true);
        teachersRepository.save(teacherForDelete);
    }
}
