package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Trainer> getTrainerList() {
        List<Trainer> trainers = new ArrayList<>();
        String query = "SELECT member_id, name, last_name,group_number FROM gymmembers";


        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                int groupNumber = resultSet.getInt("groupNumber");

                Trainer trainer = new Trainer(id, name, lastname,groupNumber);
                trainers.add(trainer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainers;
    }
    public static boolean authenticateVisitor(String firstName, String lastName) {
        return authenticateUser(firstName, lastName, "gymmembers");
    }

    public static boolean authenticateTrainer(String firstName, String lastName) {
        return authenticateUser(firstName, lastName, "trainers");
    }


    private static boolean authenticateUser(String firstName, String lastName, String table) {
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + table + " WHERE first_name = ? AND last_name = ?")) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
