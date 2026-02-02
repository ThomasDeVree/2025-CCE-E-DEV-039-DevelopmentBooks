package com.bnppf.developmentbooks.service;

import com.bnppf.developmentbooks.model.Book;
import com.bnppf.developmentbooks.model.BookQuantity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 0.0,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25);

    @Override
    public String getPriceForBasket(List<BookQuantity> basket) {
        basket = mergeDuplicates(basket);

        // remove if count of books is 0 or negative
        basket.removeIf(entry -> entry.getQuantity() <= 0);
        int totalDifferentBooks = basket.size();

        if (totalDifferentBooks == 0) return "The basket is empty";

        double sumOfUniqueBook = basket.stream().mapToDouble(value -> value.getBook().getPrice()).sum();

        double priceDiscount = sumOfUniqueBook * (1 - DISCOUNTS.get(totalDifferentBooks));
        double priceWithoutDiscount = basket.stream().filter(e -> e.getQuantity() > 1).mapToDouble(e -> (e.getQuantity() - 1) * e.getBook().getPrice()).sum();

        return priceDiscount + priceWithoutDiscount + " EUR";
    }

    public List<BookQuantity> mergeDuplicates(List<BookQuantity> basket) {
        Map<Book, Integer> merged = new HashMap<>();
        for (BookQuantity bq : basket) {
            merged.merge(bq.getBook(), bq.getQuantity(), Integer::sum);
        }

        return merged.entrySet().stream().map(e -> new BookQuantity(e.getKey(), e.getValue())).collect(Collectors.toList());
    }
}
