package com.example.ob_rest_datajpa.service;

import com.example.ob_rest_datajpa.entities.Book;

public class BookPriceCalculator {
    public double calculatePrice(Book book) {
        double price = book.getPrice();
        if (book.getPages() > 300) {
            price += 5;
        }
        //Envio
        price += 2.99;
        return price;
    }
}
