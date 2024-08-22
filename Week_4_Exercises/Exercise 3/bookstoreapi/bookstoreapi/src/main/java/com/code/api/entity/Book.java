package com.code.api.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
@Column(name="title",nullable = false,unique = true,length=20)
    private String title;
@Column(name="author",nullable = false,length=20)
    private String author;
@Column(name="price",nullable = false)
    private double price;
@Column(name="isbn",nullable = false,unique = true,length=20)
    private String isbn;
}
