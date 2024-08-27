package com.code.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.code.api.entity.Book;
import com.code.api.repository.BookRepository;
import com.code.api.exception.BookIsbnAlreadyExitsException;
import com.code.api.exception.ResourceNotFoundException;
@Service

public class BookService {
	@Autowired
	BookRepository bookRepository;

	private final List<Book> books = List.of(
	        new Book(1,"java", "Test", 950.00, "isbn-123456789",null),
	        new Book(2,"Advance Java", "test1", 450.23, "isbn-895630",null)
	    );
    public List<Book> getAllBooks() {
        // Logic to fetch all books
        return List.of(
            new Book(1,"Test1", "abc1", 639.23, "ISBN123456",null),
            new Book(2,"test2", "abc2", 593.12, "ISBN789632",null)
        );
    }
    public List<Book> getBooks()
    {
    	return bookRepository.findAll();
    }
    public Book getBookId(int id)
    {
    	return bookRepository.findById(id).
    			orElseThrow(() -> new ResourceNotFoundException("Book not found with id" + id));
    
    }
    public Book getBookById(int id) {
        return books.stream()
            .filter(book -> book.getId()==id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> filterBooks(String title, String author) {
        return books.stream()
            .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                            (author == null || book.getAuthor().equalsIgnoreCase(author)))
            .collect(Collectors.toList());
    }
    public List<Book> filterBooks1(String title, String author) {
    	List<Book> book1s= bookRepository.findAll();
        return book1s.stream()
            .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                            (author == null || book.getAuthor().equalsIgnoreCase(author)))
            .collect(Collectors.toList());
    }
    public Book addBook(Book book)
    {
    	if(bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new BookIsbnAlreadyExitsException("Book already exits witth ISBN No" + book.getIsbn());
        }
    	return bookRepository.save(book);
    }
    public Book updateBook(int bookid,Book book)
    {
    	Book oldbook= bookRepository.findById(bookid).get();
    	if(oldbook==null)
    	{
    		return null;
    	}
    	
    	oldbook.setAuthor(book.getAuthor());
    	oldbook.setIsbn(book.getIsbn());
    	oldbook.setPrice(book.getPrice());
    	oldbook.setTitle(book.getTitle());
    	return bookRepository.save(oldbook);
    }
    public String deleteBook(int id)
    {
    	Book book= bookRepository.findById(id).get();
    	if(book==null)
    	{
    		return "Book Id"+id+" not found";
    	}
    	bookRepository.delete(book);
    	return "Book Id"+id+" is deleted successfully";
    }
}
