package com.example.db.repositories;

import com.example.db.dao.UsersDao;
import com.example.db.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcTemplateImpl implements UsersDao {

    private JdbcTemplate template;

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM fix_user";

    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM fix_user where id=?";

    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO fix_user (first_name, last_name) VALUES (?, ?)";

    //language=SQL
    private final String SQL_SELECT_ALL_BY_FIRST_NAME = "SELECT * FROM fix_user WHERE first_name = ?";

    //language=SQL
    private final String SQL_UPDATE_USER = "UPDATE fix_users SET first_name = ?, last_name = ? WHERE id = ?";

    //language=SQL
    private final String SQL_DELETE_USER = "DELETE FROM fix_user WHERE id = ?";

    RowMapper<User> userRowMapper = (resultSet, i) -> {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
    };

    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_ALL_BY_FIRST_NAME, userRowMapper, firstName);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT_ALL, userRowMapper);
    }
}
