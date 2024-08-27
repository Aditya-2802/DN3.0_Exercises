package com.code.api.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO extends RepresentationModel<BookDTO>{
	private int id;
    private String title;
    private String author;
    private double price;
    private String isbn;
    @JsonGetter("price")
    public String getFormattedPrice() {
        return String.format("%.2f", price);
    }

    // Custom setter for price
    @JsonSetter("price")
    public void setFormattedPrice(String price) {
        this.price = Double.parseDouble(price);
    }
}
