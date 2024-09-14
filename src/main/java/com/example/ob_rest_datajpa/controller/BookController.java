package com.example.ob_rest_datajpa.controller;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repository.BookRepository;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    //Atributos
    private BookRepository bookRepository;

    //Constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //CRUD sobre la entidad Book
    //Buscar todos los libros

    /**
     * Buscar todo los libros
     * S
     *
     * @return List
     */
    @GetMapping("/api/books")
    public List<Book> findAll() {
        //Recuperar y obtener los libros de base de datos
        return bookRepository.findAll();
    }

//    public Book findOneById(Long id){
//        return  bookRepository.findById(id);
//    }

}
