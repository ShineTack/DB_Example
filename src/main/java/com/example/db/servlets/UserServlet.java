package com.example.db.servlets;

import com.example.db.dao.UsersDao;
import com.example.db.models.User;
import com.example.db.repositories.UsersDaoJdbcTemplateImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UsersDao usersDaoJdbc;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users;

        if (req.getParameter("firstName") != null) {
            String firstName = req.getParameter("firstName");

            users = usersDaoJdbc.findAllByFirstName(firstName);
        } else {
            users = usersDaoJdbc.findAll();
        }
        req.setAttribute("usersFromServer", users);

        req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("firstName") != null && req.getParameter("lastName") != null) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            usersDaoJdbc.save(new User(firstName, lastName));
        }
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));

            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String dbUrl = properties.getProperty("db.url");
            String classPath = properties.getProperty("db.classPath");

            DriverManagerDataSource dataSource = new DriverManagerDataSource();

            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(classPath);

            usersDaoJdbc = new UsersDaoJdbcTemplateImpl(dataSource);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
