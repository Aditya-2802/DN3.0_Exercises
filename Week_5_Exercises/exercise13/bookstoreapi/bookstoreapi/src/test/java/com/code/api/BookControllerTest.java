package com.code.api;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.code.api.dto.BookDTO;
import com.code.api.entity.Book;
import com.code.api.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
//@ContextConfiguration(classes=Application.class)
@AutoConfigureMockMvc
public class BookControllerTest {
@Autowired
 private MockMvc mockMvc;
@MockBean
 private BookService bookService;
private static ObjectMapper mapper = new ObjectMapper();
@Test
 public void testGetExample() throws Exception {
  List<BookDTO> books = new ArrayList<>();
  BookDTO book = new BookDTO();
  book.setId(1);
  book.setTitle("java");
  book.setAuthor("Abcd");
  book.setIsbn("12345678");
  book.setPrice(5000);
  books.add(book);
  Mockito.when(bookService.getBooks()).thenReturn(books);
  mockMvc.perform(MockMvcRequestBuilders
			.get("/api/books/")
			.accept(MediaType.APPLICATION_JSON))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
    .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.equalTo("java")));
   
 }
@Test
public void getBookByIdAPI() throws Exception 
{
	Book book = new Book();
	book.setId(1);
	  book.setTitle("java");
	  book.setAuthor("Abcd");
	  book.setIsbn("12345678");
	  book.setPrice(5000);
	Mockito.when(bookService.getBookId(1)).thenReturn(book);
	mockMvc.perform( MockMvcRequestBuilders
	      .get("/api/books/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
     // .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
}

@Test
public void testPostExample() throws Exception {
	Book book = new Book();
	book.setId(1);
	  book.setTitle("testing Java3");
	  book.setAuthor("Abcd");
	  book.setIsbn("1234567890123");
	  book.setPrice(5000);
 Mockito.when(bookService.addBook(ArgumentMatchers.any())).thenReturn(book);
 String json = mapper.writeValueAsString(book);
 mockMvc.perform(post("/api/books/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
   .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
   .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
   .andExpect(jsonPath("$.title", Matchers.equalTo("testing Java3")));
}

}