package org.example;

public class Trainer extends User {

    private int groupNumber;

    public Trainer(int id, String name, String lastname, String subscriptionExpiryDate, int groupNumber) {
        super(id, name, lastname, subscriptionExpiryDate);
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
        return "Visitor{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastname='" + getLastname() + '\'' + '\'' +
                ", groupNumber=" + groupNumber +
                '}';
    }
}
