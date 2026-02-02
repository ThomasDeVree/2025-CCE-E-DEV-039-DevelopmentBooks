package com.bnppf.developmentbooks.service;

import com.bnppf.developmentbooks.model.BookQuantity;

import java.util.List;

public interface BookStoreService {

    String getPriceForBasket(List<BookQuantity> bookQuantity);
}
