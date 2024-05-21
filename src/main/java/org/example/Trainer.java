package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Trainer extends User {

    private int groupNumber;

    public Trainer(int id, String name, String lastname, int groupNumber) {
        super(id, name, lastname);
        this.groupNumber = groupNumber;
    }
    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString() {
        return "Trainers: {" +
                "id = " + getId() +
                ", name = '" + getName() + '\'' +
                ", lastname = '" + getLastname() + '\'' + '\'' +
                ", groupNumber = " + groupNumber +
                '}';
    }
    public static List<Trainer> getTrainerList() {
        List<Trainer> trainers = new ArrayList<>();
        String query = "SELECT * FROM trainers";


        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String name = resultSet.getString("name");
                String last_name = resultSet.getString("last_name");
                int group_number = resultSet.getInt("group_number");

                Trainer trainer = new Trainer(id,name,last_name,group_number);


                trainers.add(trainer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainers;
    }
}
