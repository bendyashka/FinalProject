package org.example;

public class Book {
    private int id;
    private String title;
    private double price;
    private boolean isAvailable;
    private String availableDate;

    public Book(int id, String title, double price, boolean isAvailable, String availableDate) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.isAvailable = isAvailable;
        this.availableDate = availableDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    @Override
    public String toString() {
        return "Book: {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", availableDate='" + availableDate + '\'' +
                '}';
    }
}
