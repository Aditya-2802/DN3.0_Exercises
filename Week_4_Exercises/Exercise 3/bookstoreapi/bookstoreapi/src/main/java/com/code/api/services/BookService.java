package com.code.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.code.api.entity.Book;
import com.code.api.repository.BookRepository;

@Service

public class BookService {


	private final List<Book> books = List.of(
	        new Book(1,"java", "Test", 950.00, "isbn-123456789"),
	        new Book(2,"Advance Java", "test1", 450.23, "isbn-895630")
	    );
    public List<Book> getAllBooks() {
        // Logic to fetch all books
        return List.of(
            new Book(1,"Test1", "abc1", 639.23, "ISBN123456"),
            new Book(2,"test2", "abc2", 593.12, "ISBN789632")
        );
    }
    
    public Book getBookById(int bookid) {
    	//reading one by one objects from the collection books
       for (Book book :books)
       {
    	   //checking the id , if match return the object
    	   if(book.getId()==bookid) {
    		   return book;
    	   }
       }
       //null if no object is found
       return null;
    }

    public List<Book> filterBooks(String title, String author) {
        return books.stream()
            .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                            (author == null || book.getAuthor().equalsIgnoreCase(author)))
            .collect(Collectors.toList());
    }
   
    
}
