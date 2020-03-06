package com.example.db.repositories;

import com.example.db.dao.UsersDao;
import com.example.db.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcImpl implements UsersDao {

    private Connection connection;

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

    public UsersDaoJdbcImpl(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return this.findAll(SQL_SELECT_ALL_BY_FIRST_NAME);
    }

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> user;

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Integer idFromDb = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                return Optional.of(new User(idFromDb, firstName, lastName));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setInt(3, model.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<User> findAll(String query) {
        List<User> users = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_SELECT_ALL);

            while (result.next()) {
                Integer id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");

                User user = new User(id, firstName, lastName);

                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return this.findAll(SQL_SELECT_ALL);
    }
}
