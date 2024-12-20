package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
    private String login;
    private String password;

    public Visitor(int id, String name, String lastName, String login, String password) {
        super(id, name, lastName);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Visitor: {" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public static List<Visitor> getVisitorList() {
        List<Visitor> visitors = new ArrayList<>();
        String query = "SELECT * FROM visitors";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");

                Visitor visitor = new Visitor(id, name, lastName, login, password);
                visitors.add(visitor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visitors;
    }


    public static boolean authenticateVisitor(String login, String password) {
        List<Visitor> visitors = getVisitorList();


        for (Visitor visitor : visitors) {
            if (visitor.getLogin().equals(login) && visitor.getPassword().equals(password)) {
                return true;
            }
        }


        return false;
    }
}


