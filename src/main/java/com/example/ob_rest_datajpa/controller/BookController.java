package com.example.ob_rest_datajpa.controller;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repository.BookRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
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

    /**
     * Devuelve un libro con el parámetro ID
     *
     * @param id
     * @return
     */
    @GetMapping("api/books/{id}")
    @Operation(summary = "Esto es un summary", description = "Esto es una descripción")
    public ResponseEntity<Book> findOneById( @Parameter(description = "Descripcion de @Parameter") @PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
//         if (bookOpt.isPresent())
//            return ResponseEntity.ok(bookOpt.get());
//        else
//            return ResponseEntity.notFound().build();
        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo libro en base de datos
     *
     * @param book
     * @return new book
     */
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        //Guardar el libro recibido por parámetro en la base de datos
        if (book.getId() != null) {
            //Quiere decir que existe el id y por lo tanto no es una creacion
            log.warn("trying to crate a book by Id");
            System.out.println("trying to crate a book by Id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    /**
     * Metodo UPDATE para actualizar un libro
     *
     * @param book
     * @return
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        if (book.getId() == null) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build(); //400
        }
        if (!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non existe book");
            return ResponseEntity.notFound().build();//404
        }
        //Proceso de Actualizacion
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }
    /**
     * Metodo DELETE para eliminar un libro
     *
     * @param id
     * @return
     */
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();//404
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();//2xx
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            log.warn("Trying to delete a non existent books");
            return ResponseEntity.badRequest().build();
        }
        log.info("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
