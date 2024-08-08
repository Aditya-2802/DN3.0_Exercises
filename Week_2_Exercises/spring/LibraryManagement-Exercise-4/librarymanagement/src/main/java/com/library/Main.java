package com.library;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.service.BookService;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) 
    {

        // Load Spring context from applicationContext.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve  BookService bean from the context
        BookService bookService = (BookService) context.getBean("bookService");
        // configuration test
        bookService.performService();
    }    
}