package com.example.db.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

    private Connection connection;

    private Statement statement;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String dbUrl = properties.getProperty("db.url");
            String classPath = properties.getProperty("db.classPath");

            Class.forName(classPath);
            connection = DriverManager.getConnection(dbUrl, username, password);
            statement = connection.createStatement();

        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");

        if(firstName.isEmpty() || lastName.isEmpty()) {
            doGet(req, resp);
        } else {
            try {
                statement.execute(
                        String.format("INSERT INTO fix_user (first_name, last_name) values ('%s', '%s')",
                                firstName,
                                lastName
                        ));
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
