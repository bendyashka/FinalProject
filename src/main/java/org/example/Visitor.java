package org.example;

import java.time.LocalDate;
import java.util.Date;

public class Visitor {
    int id;
    String name;
    String lastname;
    String subscription_expiry_date;
    boolean is_subscription_active;
    int group_number;

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", subscription_expiry_date=" + subscription_expiry_date +
                ", is_subscription_active=" + is_subscription_active +
                ", group_number=" + group_number +
                '}';
    }

    public Visitor(){

    }

    public Visitor(int id, String name, String lastname, String subscription_expiry_date, boolean is_subscription_active, int group_number) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.subscription_expiry_date = subscription_expiry_date;
        this.is_subscription_active = is_subscription_active;
        this.group_number = group_number;

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

    public String getSubscription_expiry_date() {
        return subscription_expiry_date;
    }

    public void setSubscription_expiry_date(String subscription_expiry_date) {
        this.subscription_expiry_date = subscription_expiry_date;
    }

    public boolean isIs_subscription_active() {
        return is_subscription_active;
    }

    public void setIs_subscription_active(boolean is_subscription_active) {
        this.is_subscription_active = is_subscription_active;
    }

    public int getGroup_number() {
        return group_number;
    }

    public void setGroup_number(int group_number) {
        this.group_number = group_number;
    }
}

