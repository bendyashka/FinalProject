package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static final String url = "jdbc:mysql://localhost:3306/gym_management";
    private static final String user = "root";
    private static final String password = "0000";
    public static Connection getConnection() {
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return connection;

    }
}
