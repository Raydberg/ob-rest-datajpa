package com.example.ob_rest_datajpa.service;

import com.example.ob_rest_datajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {
        //Configuracion de la prueba
        Book book = new Book(1L, "PruebaTest", "YourBook", 1000, 49.9, LocalDate.now(), true);
        BookPriceCalculator calculator = new BookPriceCalculator();
        //Se ejecuta el comportamiento a testear
        double price = calculator.calculatePrice(book);
        System.out.println(price);
        //Comprobaciones
        assertTrue(price > 0);
        assertEquals(57.89, price);

    }
}