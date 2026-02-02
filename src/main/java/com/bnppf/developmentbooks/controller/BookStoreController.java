package com.bnppf.developmentbooks.controller;

import com.bnppf.developmentbooks.model.BookQuantity;
import com.bnppf.developmentbooks.service.BookStoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookStoreController {
    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/price")
    public String getBasketPrice(@RequestBody List<BookQuantity> request) {
        return bookStoreService.getPriceForBasket(request);
    }

}
