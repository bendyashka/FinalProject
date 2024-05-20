package org.example;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Visitor extends User {
    private boolean isSubscriptionActive;
    private int groupNumber;

    public Visitor(int id, String name, String lastname, String subscriptionExpiryDate, boolean isSubscriptionActive, int groupNumber) {
        super(id, name, lastname, subscriptionExpiryDate);
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
        return "Visitor{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", subscriptionExpiryDate='" + getSubscriptionExpiryDate() + '\'' +
                ", isSubscriptionActive=" + isSubscriptionActive +
                ", groupNumber=" + groupNumber +
                '}';
    }
}
