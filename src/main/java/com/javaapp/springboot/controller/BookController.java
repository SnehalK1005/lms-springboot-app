package com.javaapp.springboot.controller;

import com.javaapp.springboot.model.Book;
import com.javaapp.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    //Get All Books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    //Create / Add Books
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}
