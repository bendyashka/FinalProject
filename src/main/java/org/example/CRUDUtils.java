package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    public static List<Visitor> saveVisitorData(String name, String lastname, String subscription_expiry_date, boolean is_subscription_active, int group_number){
        List<Visitor> visitors = new ArrayList<>();
        String INSERT_PRODUCT = "INSERT INTO gymmembers (name, last_name, subscription_expiry_date, is_subscription_active, group_number) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, subscription_expiry_date);
            preparedStatement.setBoolean(4, is_subscription_active);
            preparedStatement.setInt(5, group_number);
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно добавлены в базу данных.");
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return visitors;

    }

}
