package com.code.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.api.entity.Book;
import com.code.api.services.BookService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/books")
public class BookController {
private BookService bookService;
public BookController(BookService bookService) {
    this.bookService = bookService;
}


    @GetMapping
    public List<Book> getAllBooks() {
        // Assume you have a service that fetches all books
        return bookService.getAllBooks();
    }
    @GetMapping("/search")
    public List<Book> filterBooks(@RequestParam(required = false) String title, 
                                  @RequestParam(required = false) String author) {
        return bookService.filterBooks(title, author);
    }
    @GetMapping("/")
    public List<Book> getBooks()
    {
    	return bookService.getBooks();
    }
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book)
    {
    	return bookService.addBook(book);
    }
    @PutMapping("/edit/{id}")
    public Book addBook(@PathVariable int id, @RequestBody Book book)
    {
    	return bookService.updateBook(id,book);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteBook(int id) 
    {
    	return bookService.deleteBook(id);
    }
}
