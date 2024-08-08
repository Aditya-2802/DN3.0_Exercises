package com.library.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.api.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
