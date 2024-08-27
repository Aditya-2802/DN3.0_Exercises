package com.code.api.json;

import java.io.IOException;

import com.code.api.dto.BookDTO;
import com.code.api.entity.Book;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomSerializer extends StdSerializer<BookDTO> {

    protected CustomSerializer(Class<BookDTO> t) {
        super(t);
    }
    public CustomSerializer(){
        this(null);
    }

    @Override
    public void serialize(BookDTO bookdto, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("Book Id",bookdto.getId());
        gen.writeStringField("Title",bookdto.getTitle());
        gen.writeStringField("Author",bookdto.getAuthor());
        gen.writeNumberField("Price", bookdto.getPrice());
        gen.writeStringField("ISBN",bookdto.getIsbn());
    }
}