package com.code.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.code.api.entity.Book;

import jakarta.persistence.LockModeType;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
//	@Override
//  @Lock(value = LockModeType.PESSIMISTIC_FORCE_INCREMENT)
//  Optional<Book> findById(Integer integer);
	@Override
  @Lock(value = LockModeType.OPTIMISTIC)
  Optional<Book> findById(Integer integer);
	 Optional<Book> findByIsbn(String isbn);
}
