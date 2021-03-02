package ru.itis.rasimusv.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.rasimusv.models.User;

import java.util.*;

@Slf4j
@Profile("master")
@Repository
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<Long> {

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM users";

    //language=SQL
    private static final String SQL_SELECT_ALL_WITH_PAGES = "SELECT * FROM users ORDER BY id LIMIT :limit OFFSET :offset ;";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_USERNAME = "SELECT * FROM users WHERE username = :username";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_ID = "SELECT * FROM users WHERE id = :id";

    //language=SQL
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = :id;";

    //language=SQL
    private static final String SQL_ADD_USER = "INSERT INTO users (username, first_name, last_name, email, hashpassword, confirm_code, state) VALUES (:username, :firstname, :lastname, :email, :hashpassword, :confirm_code, :state)";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_CONFIRM_CODE = "SELECT * FROM users WHERE confirm_code = :confirm_code";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> new User(
            row.getLong("id"),
            row.getString("username"),
            row.getString("first_name"),
            row.getString("last_name"),
            row.getString("email"),
            row.getString("hashpassword"),
            row.getString("confirm_code"),
            User.State.valueOf(row.getString("state")));

    public UsersRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", user.getUsername());
        map.addValue("firstname", user.getFirstName());
        map.addValue("lastname", user.getLastName());
        map.addValue("email", user.getEmail());
        map.addValue("hashpassword", user.getHashPassword());
        map.addValue("confirm_code", user.getConfirmCode());
        map.addValue("state", user.getState().toString());
        log.error(map.toString());
        jdbcTemplate.update(SQL_ADD_USER, map);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER, Collections.singletonMap("id", id));
    }

    @Override
    public void update(User user) {
        delete(user.getId());
        save(user);
    }

    @Override
    public List<User> findById(Long id) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_ID, Collections.singletonMap("id", id), userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findAll(int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", size);
        params.put("offset", page * size);
        return jdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, params, userRowMapper);
    }

    @Override
    public List<User> findByUsername(String username) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_USERNAME, Collections.singletonMap("username", username), userRowMapper);
    }

    public List<User> findByConfirmCode(String confirmCode) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_CONFIRM_CODE, Collections.singletonMap("confirm_code", confirmCode), userRowMapper);
    }
}
