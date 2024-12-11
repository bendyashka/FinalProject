package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {


    public static void saveBook(String title, double price, boolean isAvailable, String availableDate) {
        String INSERT_BOOK = "INSERT INTO books (title, price, is_available, available_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK)) {
            preparedStatement.setString(1, title);
            preparedStatement.setDouble(2, price);
            preparedStatement.setBoolean(3, isAvailable);
            preparedStatement.setString(4, availableDate);
            preparedStatement.executeUpdate();
            System.out.println("Книга успешно добавлена в базу данных.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean authenticateWorker(String login, String password) {

        String query = "SELECT * FROM workers WHERE login = ? AND password = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);


            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void saveVisitor(String name, String lastName, String login, String password, String membershipExpiryDate, boolean membershipActive) {
        String query = "INSERT INTO visitors (name, last_name, login, password, membership_expiry_date, membership_active) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, membershipExpiryDate);
            preparedStatement.setBoolean(6, membershipActive);


            preparedStatement.executeUpdate();
            System.out.println("Visitor has been added successfully to the database.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to save the visitor.");
        }
    }


    public static void saveWorker(String name, String lastname, String login, String password) {
        String INSERT_WORKER = "INSERT INTO workers (name, last_name, login, password) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORKER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
            System.out.println("Сотрудник успешно добавлен в базу данных.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public static boolean authenticateAdmin(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
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

    public static void updateAdminPassword(String username, String newPassword) {
        String updateQuery = "UPDATE admin SET password = ? WHERE username = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.executeUpdate();
            System.out.println("Пароль администратора успешно обновлён.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Book> getBookList() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                double price = resultSet.getDouble("price");
                boolean isAvailable = resultSet.getBoolean("is_available");
                String availableDate = resultSet.getString("available_date");

                Book book = new Book(id, title, price, isAvailable, availableDate);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    public static void updateBookAvailability(int bookId, boolean isAvailable, java.sql.Date availableDate) {
        String query = "UPDATE books SET is_available = ?, available_date = ? WHERE id = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, isAvailable);
            if (isAvailable) {
                stmt.setNull(2, java.sql.Types.DATE);
            } else {
                stmt.setDate(2, availableDate);
            }
            stmt.setInt(3, bookId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book availability updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean authenticateVisitor(String login, String password) {
        String query = "SELECT * FROM visitors WHERE login = ? AND password = ?";

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



    public static boolean borrowBook(int bookId) {
        String updateQuery = "UPDATE visitors SET borrowed_book_id = ? WHERE borrowed_book_id IS NULL LIMIT 1";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setInt(1, bookId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                updateBookAvailability(bookId, false, Date.valueOf("NULL"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean returnBook(int bookId) {
        String updateQuery = "UPDATE visitors SET borrowed_book_id = NULL WHERE borrowed_book_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setInt(1, bookId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                updateBookAvailability(bookId, true, Date.valueOf("CURRENT_DATE")); // Предполагается, что возврат делает книгу доступной
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
