/**
 * @author Snehal Kotian
 **/
package com.javaapp.springboot.controller;

import com.javaapp.springboot.exception.ResourceNotFoundException;
import com.javaapp.springboot.model.Book;
import com.javaapp.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //Get Book by the Id
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id: " + id));
        return ResponseEntity.ok(book);
    }
    //Update book
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id: " + id));
        book.setAuthor(bookDetails.getAuthor());
        book.setTitle(bookDetails.getTitle());
        book.setDescription(bookDetails.getDescription());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrice(bookDetails.getPrice());
        book.setPublisher(bookDetails.getPublisher());
        book.setQuantity(bookDetails.getQuantity());
        Book bookUpdated = bookRepository.save(book);
        return ResponseEntity.ok(bookUpdated);
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id: " + id));
        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/books/search")
    public List<Book> searchBooks(@RequestParam String field, @RequestParam String value) {
        return switch (field) {
            case "title" -> bookRepository.findByTitleContaining(value);
            case "author" -> bookRepository.findByAuthorContaining(value);
            case "publisher" -> bookRepository.findByPublisherContaining(value);
            default -> throw new IllegalArgumentException("Invalid search field: " + field);
        };
    }
}
