/**
 * @author Snehal Kotian
 **/
package com.javaapp.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "BOOK_DATA")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private int id;
    @Column(name = "BOOK_TITLE",  nullable = false, length = 50)
    private String title;
    @Column(name = "BOOK_AUTHOR", nullable = false, length = 50)
    private String author;
    @Column(name = "BOOK_PUBLISHER", nullable = false, length = 50)
    private String publisher;
    @Column(name = "BOOK_PRICE")
    private Integer price;
    @Column(name = "BOOK_QUANTITY")
    private Integer quantity;
    @Column(name = "BOOK_ISBN", nullable = false, length = 50, unique = true)
    private String isbn;
    @Column(name = "BOOK_DESCRIPTION", nullable = false, length = 2000)
    private String description;
}
