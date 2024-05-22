package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    public static void saveVisitorData(String name, String lastname, String subscriptionExpiryDate, boolean isSubscriptionActive, int groupNumber, String loginMember, String passwordMember) {
        String INSERT_USER = "INSERT INTO gymmembers (name, last_name, subscription_expiry_date, is_subscription_active, group_number, login_member, password_member) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, subscriptionExpiryDate);
            preparedStatement.setBoolean(4, isSubscriptionActive);
            preparedStatement.setInt(5, groupNumber);
            preparedStatement.setString(6, loginMember);
            preparedStatement.setString(7, passwordMember);
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно добавлены в базу данных.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean authenticateVisitor(String loginMember, String passwordMember) {
        return authenticateUser(loginMember, passwordMember, "gymmembers", "login_member", "password_member");
    }

    public static boolean authenticateTrainer(String loginTrainer, String passwordTrainer) {
        return authenticateUser(loginTrainer, passwordTrainer, "trainers", "login_trainer", "password_trainer");
    }


    private static boolean authenticateUser(String login, String password, String table, String loginColumn, String passwordColumn) {
        String query = "SELECT * FROM " + table + " WHERE " + loginColumn + " = ? AND " + passwordColumn + " = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void saveTrainerData(String name, String lastname, int groupNumber, String loginTrainer, String passwordTrainer) {
        String INSERT_USER = "INSERT INTO trainers (name, last_name, group_number, login_trainer, password_trainer) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setInt(3, groupNumber);
            preparedStatement.setString(4, loginTrainer);
            preparedStatement.setString(5, passwordTrainer);
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно добавлены в базу данных.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Trainer> getTrainerList() {
        List<Trainer> trainers = new ArrayList<>();
        String query = "SELECT member_id, name, last_name, group_number FROM trainers"; // Изменил на 'trainers'

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("last_name");
                int groupNumber = resultSet.getInt("group_number");

                Trainer trainer = new Trainer(id, name, lastname, groupNumber);
                trainers.add(trainer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainers;
    }
    public static boolean authenticateAdmin(String username, String password) {
        String query = "SELECT * FROM password_admin WHERE username = ? AND password = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateAdminPassword(String newPassword) {
        String updateQuery = "UPDATE password_admin SET password = ? WHERE username = 'admin'";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setString(1, newPassword);
            stmt.executeUpdate();
            System.out.println("Admin password updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
