package com.example.library.model;

public class Book {
    private int id;
private String title;
private String author;
private double price;
    private String category;
    private boolean borrowed;

    public Book(int id, String title, String author, double price, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.borrowed = false;
    }

    // getter / setter
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isBorrowed() { return borrowed; }

    public void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }

    @Override
    public String toString() {
        String status = borrowed ? "已借出" : "可借阅";
        return String.format("[%d] 《%s》- %s | ¥%.2f | %s | %s",
                id, title, author, price, category, status);
    }
}
