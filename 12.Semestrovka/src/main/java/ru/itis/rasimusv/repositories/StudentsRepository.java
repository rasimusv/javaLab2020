package ru.itis.rasimusv.repositories;

import ru.itis.rasimusv.models.Student;

public interface StudentsRepository<ID> extends CrudRepository<Student, ID> {
}
