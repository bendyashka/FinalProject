package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/librarydb";
    private static final String user = "root";
    private static final String password = "Dastanchik7";
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
