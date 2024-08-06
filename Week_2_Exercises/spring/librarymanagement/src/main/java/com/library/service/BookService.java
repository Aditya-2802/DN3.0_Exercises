package com.library.service;
import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for dependency injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Example method to demonstrate functionality
    public void performService() {
        bookRepository.displayRepositoryInfo();
    }

}