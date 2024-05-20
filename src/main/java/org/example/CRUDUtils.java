package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDUtils {
    public static void saveVisitorData(String name, String lastname, String subscriptionExpiryDate, boolean isSubscriptionActive, int groupNumber) {
        String INSERT_USER = "INSERT INTO gymmembers (name, last_name, subscription_expiry_date, is_subscription_active, group_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, subscriptionExpiryDate);
            preparedStatement.setBoolean(4, isSubscriptionActive);
            preparedStatement.setInt(5, groupNumber);
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно добавлены в базу данных.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void saveTrainerData(String name, String lastname, int groupNumber) {
        String INSERT_USER = "INSERT INTO trainers (name, last_name, group_number) VALUES (?, ?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, groupNumber);
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно добавлены в базу данных.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
