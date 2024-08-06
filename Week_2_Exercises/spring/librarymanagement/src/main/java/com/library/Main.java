package com.library;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;
import com.library.repository.BookRepository;

public class Main {
    public static void main(String[] args) {
    // Load the Spring context from the XML configuration file
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    // Retrieve the beans from the Spring context
    BookService bookService = (BookService) context.getBean("bookService");
    BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");

    // Test the configuration

    bookService.printService();
    bookRepository.printRepository();

}

}