package com.example.ob_rest_datajpa;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
        BookRepository repository = context.getBean(BookRepository.class);
        //CRUD
        //Crear libros
        Book book = new Book(null, "Book1", "Ray", 50, 12.8, LocalDate.of(2005, 5, 15), true);
        Book book1 = new Book(null, "Book2", "Ryan", 30, 67.8, LocalDate.of(2004, 7, 20), false);

        System.out.println("Num de libros : " + repository.findAll().size());
        //Guardas libros
        repository.save(book);
        repository.save(book1);
        System.out.println("Num de libros : " + repository.findAll().size());
    }

}
