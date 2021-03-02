package ru.itis.rasimusv.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.rasimusv.models.Student;

import java.util.*;

@Profile("master")
@Repository
public class StudentsRepositoryJdbcTemplateImpl implements StudentsRepository<Long> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM student";

    //language=SQL
    private static final String SQL_ADD_STUDENT = "INSERT INTO student (id, first_name, last_name, age, group_number) VALUES (:id, :first_name, :last_name, :age, :group_number)";

    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "SELECT * FROM student ORDER BY id LIMIT :limit OFFSET :offset ;";

    //language=SQL
    private static final String SQL_DELETE_STUDENT = "DELETE FROM student WHERE id = :id;";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_ID = "SELECT * FROM student WHERE id = :id";

    private final RowMapper<Student> studentRowMapper = (row, rowNumber) -> Student.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    public StudentsRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Student student) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", student.getId());
        map.addValue("first_name", student.getFirstName());
        map.addValue("last_name", student.getLastName());
        map.addValue("age", student.getAge());
        map.addValue("group_number", student.getGroupNumber());
        jdbcTemplate.update(SQL_ADD_STUDENT, map);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_STUDENT, Collections.singletonMap("id", id));
    }

    @Override
    public void update(Student student) {
        delete(student.getId());
        save(student);
    }

    @Override
    public List<Student> findById(Long id) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_ID, Collections.singletonMap("id", id), studentRowMapper);
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, studentRowMapper);
    }

    @Override
    public List<Student> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return jdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, studentRowMapper);
    }

}