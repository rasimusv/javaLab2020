package ru.itis.rasimusv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rasimusv.models.Student;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String groupNumber;

    public static StudentDto from(Student student) {
        if (student == null) {
            return null;
        }
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .groupNumber(student.getGroupNumber())
                .build();
    }

    public static List<StudentDto> from(List<Student> students) {
        return students.stream()
                .map(StudentDto::from)
                .collect(Collectors.toList());
    }
}
