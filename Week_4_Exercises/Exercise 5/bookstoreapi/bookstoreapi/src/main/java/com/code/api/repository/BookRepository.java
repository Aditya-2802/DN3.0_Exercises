package com.code.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.api.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


}
