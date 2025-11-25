package com.example.cv_builder;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:cv_builder.db";
    private static Database instance;

    private Database() {
        init();
    }

    public static synchronized Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    private void init() {
        try (Connection c = getConnection();
             Statement s = c.createStatement()) {
            s.execute("CREATE TABLE IF NOT EXISTS cv (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "fullName TEXT," +
                    "jobTitle TEXT," +
                    "email TEXT," +
                    "phone TEXT," +
                    "address TEXT," +
                    "projects TEXT," +
                    "skills TEXT," +
                    "languages TEXT," +
                    "experience TEXT," +
                    "education TEXT," +
                    "imageUrl TEXT," +
                    "created_at DATETIME DEFAULT CURRENT_TIMESTAMP" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}