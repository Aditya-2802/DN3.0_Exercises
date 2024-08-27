package com.code.api.json;

import java.io.IOException;

import com.code.api.dto.BookDTO;
import com.code.api.entity.Book;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomDeserializer extends StdDeserializer<BookDTO> {
    protected CustomDeserializer(Class<BookDTO> vc) {
        super(vc);
    }
    public CustomDeserializer(){
        this(null);
    }

    @Override
    public BookDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node=p.getCodec().readTree(p);
        BookDTO bookDTO=new BookDTO();
                bookDTO.setId(node.get("Book Id").asInt());
                bookDTO.setTitle(node.get("Title").asText());
                bookDTO.setAuthor(node.get("Author").asText());
                bookDTO.setIsbn(node.get("ISBN").asText());
                bookDTO.setPrice(node.get("Price").asDouble());
        return bookDTO;
    }
}