package com.library.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.repository.BookRepository;

@Service

public class BookService {
    
    private BookRepository bookRepository;
    
    // Constructor for dependency injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // Setter method for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // logic methods
    public void performService() {
    // Logic that uses bookRepository
    System.out.println("Service is performing using BookRepository");
    }
}