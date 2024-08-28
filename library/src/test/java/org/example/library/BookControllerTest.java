package org.example.library;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.example.library.entity.Author;
import org.example.library.controller.BookController;
import org.example.library.entity.Book;
import org.example.library.service.BookService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class BookControllerTest {
    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;
    private Book book;
    private Author author;

    @Test
    public void testCreateBook() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Vova")
                .surname("Korova")
                .birthDate(LocalDate.of(1555,11,1))
                .build();
        book = Book.builder()
                .id(1L)
                .title("Капитанская дочка")
                .author(author)
                .build();
        when(bookService.createBook(book)).thenReturn(book);
        ResponseEntity<Book> response = bookController.createBook(book);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testFindBookById(){
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Vova")
                .surname("Korova")
                .birthDate(LocalDate.of(1555,11,1))
                .build();
        book = Book.builder()
                .id(1L)
                .title("Капитанская дочка")
                .author(author)
                .build();
        when(bookService.findBookById(1L)).thenReturn(book);
        ResponseEntity<Book> response = bookController.findBook(book.getId());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testFindAllBooks() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Vova")
                .surname("Korova")
                .birthDate(LocalDate.of(1555,11,1))
                .build();
        book = Book.builder()
                .id(1L)
                .title("Капитанская дочка")
                .author(author)
                .build();
        when(bookService.findAllBooks(any(Pageable.class))).thenReturn(Collections.singletonList(book));
        ResponseEntity<List<Book>> response = bookController.findAll(0,1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(book, response.getBody().get(0));
    }

    @Test
    public void testDeleteBook() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Vova")
                .surname("Korova")
                .birthDate(LocalDate.of(1555,11,1))
                .build();
        book = Book.builder()
                .id(1L)
                .title("Капитанская дочка")
                .author(author)
                .build();
        doNothing().when(bookService).deleteBookById(book.getId());
        ResponseEntity<String> response = bookController.deleteBook(book.getId());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Deleted", response.getBody());
    }

    @Test
    public void testUpdateBook() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Vova")
                .surname("Korova")
                .birthDate(LocalDate.of(1555,11,1))
                .build();
        book = Book.builder()
                .id(1L)
                .title("Капитанская дочка")
                .author(author)
                .build();
        doNothing().when(bookService).updateBook(eq(book.getId()), any(Book.class), eq(author.getId()));
        ResponseEntity<String> response = bookController.updateBook(book.getId(), book, author.getId());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
    }
}
