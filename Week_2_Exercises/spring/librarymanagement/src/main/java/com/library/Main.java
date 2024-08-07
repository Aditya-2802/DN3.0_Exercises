package com.library;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.repository.BookRepository;
import com.library.service.BookService;

public class Main {
    public static void main(String[] args) {
              // Load the Spring context from the XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    // Retrieve the BookService bean
    BookService bookService = (BookService) context.getBean("bookService");

    // Call a method on the BookService bean to test the dependency injection
    bookService.performService();
}
}