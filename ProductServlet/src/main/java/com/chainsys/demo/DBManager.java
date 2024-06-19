package com.chainsys.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String URL = "jdbc:mysql://localhost:3306/demo";
        String USER = "root";
        String PASSWORD = "root";

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}