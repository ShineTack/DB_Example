package com.example.db.conector;

import java.sql.Connection;
import java.sql.Statement;

public class PostgresConnector {
    private static PostgresConnector postgresConnector = new PostgresConnector();
    private Connection connection;
    private Statement statement;

    private String username;
    private String password;
    private String classPath;
    private String dbUrl;

    private PostgresConnector() {

    }

    public PostgresConnector getPostgresConnector() {
        return postgresConnector;
    }

    public void openConnection() {
    }


}
