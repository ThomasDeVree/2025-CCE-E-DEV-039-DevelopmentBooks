package com.bnppf.developmentbooks.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "author", "year"})
public class Book {
    private String name;
    private String author;
    private String year;
    private double price;
}
