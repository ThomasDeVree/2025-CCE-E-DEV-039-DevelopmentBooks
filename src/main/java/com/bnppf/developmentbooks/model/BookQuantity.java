package com.bnppf.developmentbooks.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookQuantity {
    private Book book;
    private int quantity;

    public BookQuantity(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }
}
