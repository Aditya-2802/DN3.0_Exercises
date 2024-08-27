package com.code.api.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Title null not allowed")
    @Size(
    		min = 10,
    		max=50,
    		message = "Title size should be between 10 to 50"
    		)
    
@Column(name="title",nullable = false,unique = true,length=20)
    private String title;
@Column(name="author",nullable = false,length=20)
    private String author;
@Min(value = 1,message="Price should be >1")
@Column(name="price",nullable = false)
    private double price;
@Size(
		max = 13,
		min = 13,
		message = "ISBN No should be of 13 character"
		)
@Column(name="isbn",nullable = false,unique = true,length=20)
    private String isbn;

@Version
private Long version;
}
