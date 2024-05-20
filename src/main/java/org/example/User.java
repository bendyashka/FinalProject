package org.example;
import java.util.Scanner;
import java.util.InputMismatchException;

public class User {
    private int id;
    private String name;
    private String lastname;
    private String subscriptionExpiryDate;

    public User(int id, String name, String lastname, String subscriptionExpiryDate) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.subscriptionExpiryDate = subscriptionExpiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSubscriptionExpiryDate() {
        return subscriptionExpiryDate;
    }

    public void setSubscriptionExpiryDate(String subscriptionExpiryDate) {
        this.subscriptionExpiryDate = subscriptionExpiryDate;
    }
}
