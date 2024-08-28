package com.code.api.controllers;

import java.util.List;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.code.api.dto.BookDTO;
import com.code.api.entity.Book;
import com.code.api.services.BookService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/books")
public class BookController {
private BookService bookService;
public BookController(BookService bookService) {
    this.bookService = bookService;
}

@GetMapping ("/test")
public String test()
{
		return "welcome";
}
    
    @GetMapping("/search")
    public List<Book> filterBooks(@RequestParam(required = false) String title, 
                                  @RequestParam(required = false) String author) {
        return bookService.filterBooks(title, author);
    }
    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> getBooks()
    {
    	List<BookDTO> books=bookService.getBooks();
    	if(books.isEmpty())
    	{
    		
    		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    		    }

    		    return new ResponseEntity<>(books, HttpStatus.OK);
    	
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks()
    {
    	List<BookDTO> books=bookService.getBooks();
 for(BookDTO bookDTO:books)   	
 {
       
        // Add self link
        bookDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(bookDTO.getId())).withSelfRel());
        
        // Add link to all books
        bookDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books"));
 }
 return new ResponseEntity<>(books, HttpStatus.OK);
    	
    	
    }
    @PostMapping(value="/add",consumes  = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<Book> addBook(@Valid @RequestBody Book book)
    {
    	// Adding custom headers to the response
        HttpHeaders headers = new HttpHeaders();
        headers.add("MYBOOKSTORE-APP", "Bookstore API");
        headers.add("RESPONSE-TIME", String.valueOf(System.currentTimeMillis()));
        book=   bookService.addBook(book);
        return new ResponseEntity<>(book, headers, HttpStatus.OK);
        
    	//return bookService.addBook(book);
    }
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book addBook(@PathVariable int id, @RequestBody Book book)
    {
    	return bookService.updateBook(id,book);
    }
    
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)  // Sets the status code to 200 OK for successful requests
    public  ResponseEntity<Book> getBookById(@PathVariable int id) {
    	
       // return bookService.getBookById(id);
        return new ResponseEntity<>(bookService.getBookId(id), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable int id) 
    {
    	bookService.deleteBook(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
