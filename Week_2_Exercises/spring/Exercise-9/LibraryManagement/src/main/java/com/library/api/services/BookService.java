package com.library.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.api.entity.Book;
import com.library.api.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService implements IBookService {

	@Autowired
	BookRepository bookRepository;
	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Book findById(int theId) {
		// TODO Auto-generated method stub
		return bookRepository.findById(theId).get();
	}

	@Override
	public Book save(Book theBook) {
		// TODO Auto-generated method stub
		return bookRepository.save(theBook);
	}

	@Override
	public String deleteById(int theId) {
		// TODO Auto-generated method stub
Optional<Book> result = bookRepository.findById(theId);
		
		Book theBook = null;
		String message="";
		if (result.isPresent()) {
			theBook = result.get();
			bookRepository.delete(theBook);
			message="Book with id "+theId+" deleted successfully";
		}
		else {
			// we didn't find the Book
			message="Book with id "+theId+" not found";
		}
		return message;
	}

	@Override
	public Book update(Book theBook) {
		// TODO Auto-generated method stub
		return bookRepository.save(theBook);
	}

}
