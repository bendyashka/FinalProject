package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Worker extends User {
    private int sectionNumber;
    private String login;
    private String password;

    public Worker(int id, String name, String lastname, int sectionNumber, String login, String password) {
        super(id, name, lastname);
        this.sectionNumber = sectionNumber;
        this.login = login;
        this.password = password;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
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
        return "Worker: {" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", sectionNumber=" + sectionNumber +
                ", login='" + login + '\'' +
                '}';
    }

    public static List<Worker> getWorkerList() {
        List<Worker> workers = new ArrayList<>();
        String query = "SELECT * FROM workers";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("worker_id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                int sectionNumber = resultSet.getInt("section_number");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");

                Worker worker = new Worker(id, name, lastName, sectionNumber, login, password);
                workers.add(worker);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workers;
    }
}