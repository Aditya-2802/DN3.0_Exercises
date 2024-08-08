package com.library.api.services;

import java.util.List;

import com.library.api.entity.Book;



public interface IBookService {

public List<Book> findAll();
	
	public Book findById(int theId);
	
	public Book save(Book theBook);
	
	public String deleteById(int theId);
	
	public Book update(Book theBook);
}
