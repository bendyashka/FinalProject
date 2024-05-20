package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
    private boolean isSubscriptionActive;
    private int groupNumber;
    private String subscriptionExpiryDate;

    public Visitor(int id, String name, String lastname, String subscriptionExpiryDate, boolean isSubscriptionActive, int groupNumber) {
        super(id, name, lastname);
        this.subscriptionExpiryDate = subscriptionExpiryDate;
        this.isSubscriptionActive = isSubscriptionActive;
        this.groupNumber = groupNumber;
    }

    public boolean isSubscriptionActive() {
        return isSubscriptionActive;
    }

    public void setSubscriptionActive(boolean subscriptionActive) {
        isSubscriptionActive = subscriptionActive;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString() {
        return "Visitor " +
                 + getId() +
                " : " + getName() +
                " " + getLastname() +
                " " + subscriptionExpiryDate +
                " " + isSubscriptionActive +
                " " + groupNumber +
                ' ';
    }


    public String getSubscriptionExpiryDate() {
        return subscriptionExpiryDate;
    }

    public void setSubscriptionExpiryDate(String subscriptionExpiryDate) {
        this.subscriptionExpiryDate = subscriptionExpiryDate;
    }
    public static List<Visitor> getVisitorList() {
        List<Visitor> visitors = new ArrayList<>();
        String query = "SELECT * FROM gymmembers";


        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String name = resultSet.getString("name");
                String last_name = resultSet.getString("last_name");
                String subscription_Expiry_Date = resultSet.getString("subscription_Expiry_Date");
                Boolean is_Subscription_Active = resultSet.getBoolean("is_Subscription_Active");
                int group_number = resultSet.getInt("group_number");

                Visitor visitor = new Visitor(id,name,last_name,subscription_Expiry_Date,is_Subscription_Active,group_number);


                visitors.add(visitor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visitors;
    }
}
