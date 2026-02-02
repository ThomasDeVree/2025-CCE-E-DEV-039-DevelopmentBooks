package com.bnppf.developmentbooks.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private String name;
    private String author;
    private String year;
    private double price;

    public Book(String name, String author, String year, double price) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.price = price;
    }
}
