package com.javaapp.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "STORE_DATA")
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private int id;
    @ManyToOne  // Defines a many-to-one relationship
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID", nullable = false)
    private Book book;
    @Column(name = "STORE_NAME", nullable = false, length = 50)
    private String name;
    @Column(name = "STORE_ADDRESS", nullable = false, length = 300)
    private String address;
}
