package com.bnppf.developmentbooks.service;

import com.bnppf.developmentbooks.model.Book;
import com.bnppf.developmentbooks.model.BookQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookStoreServiceImplTest {

    private BookStoreServiceImpl service;

    private Book b1, b2, b3, b4, b5;

    @BeforeEach
    void setup() {
        service = new BookStoreServiceImpl();

        b1 = new Book("Clean Code", "Robert Martin", "2008", 50);
        b2 = new Book("The Clean Coder", "Robert Martin", "2011", 50);
        b3 = new Book("Clean Architecture", "Robert Martin", "2017", 50);
        b4 = new Book("TDD by Example", "Kent Beck", "2003", 50);
        b5 = new Book("Working Effectively with Legacy Code", "Michael Feathers", "2004", 50);
    }

    @Test
    void emptyBasketReturnsMessage() {
        List<BookQuantity> basket = new ArrayList<>();

        String result = service.getPriceForBasket(basket);

        assertEquals("The basket is empty", result);
    }

    @Test
    void removesBooksWithZeroOrNegativeQuantity() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 0));
        basket.add(new BookQuantity(b2, -1));

        String result = service.getPriceForBasket(basket);

        assertEquals("The basket is empty", result);
    }

    @Test
    void oneBookNoDiscount() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 1));

        String result = service.getPriceForBasket(basket);

        assertEquals("50.0 EUR", result);
    }

    @Test
    void twoTimesTheSameBook() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 1));
        basket.add(new BookQuantity(b1, 1));

        String result = service.getPriceForBasket(basket);

        assertEquals("100.0 EUR", result);
    }

    @Test
    void twoTimesTheSameBookAndAnother() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 1));
        basket.add(new BookQuantity(b1, 1));
        basket.add(new BookQuantity(b2, 1));

        String result = service.getPriceForBasket(basket);

        assertEquals("145.0 EUR", result);
    }

    @Test
    void twoDifferentBooksFivePercentDiscount() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 1));
        basket.add(new BookQuantity(b2, 1));

        // 2 books → 5% discount → 100 * 0.95 = 95
        String result = service.getPriceForBasket(basket);

        assertEquals("95.0 EUR", result);
    }

    @Test
    void threeDifferentBooksTenPercentDiscount() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 1));
        basket.add(new BookQuantity(b2, 1));
        basket.add(new BookQuantity(b3, 1));

        // 3 books → 10% discount → 150 * 0.90 = 135
        String result = service.getPriceForBasket(basket);

        assertEquals("135.0 EUR", result);
    }

    @Test
    void fourDifferentBooksTwentyPercentDiscount() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 1));
        basket.add(new BookQuantity(b2, 1));
        basket.add(new BookQuantity(b3, 1));
        basket.add(new BookQuantity(b4, 1));

        // 4 books → 20% discount → 200 * 0.80 = 160
        String result = service.getPriceForBasket(basket);

        assertEquals("160.0 EUR", result);
    }

    @Test
    void fiveDifferentBooksTwentyFivePercentDiscount() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 1));
        basket.add(new BookQuantity(b2, 1));
        basket.add(new BookQuantity(b3, 1));
        basket.add(new BookQuantity(b4, 1));
        basket.add(new BookQuantity(b5, 1));

        // 5 books → 25% discount → 250 * 0.75 = 187.5
        String result = service.getPriceForBasket(basket);

        assertEquals("187.5 EUR", result);
    }

    @Test
    void multipleQuantitiesAddsFullPriceForExtras() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 3)); // 1 counted for discount, 2 extra
        basket.add(new BookQuantity(b2, 1));

        // Unique books = 2 → 5% discount on (50 + 50) = 100 * 0.95 = 95
        // Extra copies: (3 - 1) * 50 = 100
        // Total = 95 + 100 = 195
        String result = service.getPriceForBasket(basket);

        assertEquals("195.0 EUR", result);
    }

    @Test
    void mixedScenario() {
        List<BookQuantity> basket = new ArrayList<>();
        basket.add(new BookQuantity(b1, 2));
        basket.add(new BookQuantity(b2, 3));
        basket.add(new BookQuantity(b3, 1));

        // Unique books = 3 → 10% discount on (50 + 50 + 50) = 150 * 0.90 = 135
        // Extra copies:
        // b1: (2 - 1) * 50 = 50
        // b2: (3 - 1) * 50 = 100
        // Total extras = 150
        // Total = 135 + 150 = 285
        String result = service.getPriceForBasket(basket);

        assertEquals("285.0 EUR", result);
    }
}