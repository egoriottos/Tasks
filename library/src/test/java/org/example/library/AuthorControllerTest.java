package org.example.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.example.library.controller.AuthorController;
import org.example.library.entity.Author;
import org.example.library.service.AuthorService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthorControllerTest {
    @InjectMocks
    private AuthorController authorController;
    @Mock
    private AuthorService authorService;
    private Author author;


    @Test
    public void testCreateAuthor() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .name("Alexandr")
                .surname("Pushkin")
                .birthDate(LocalDate.of(1799, Month.JUNE, 6))
                .build();
        when(authorService.createAuthor(any())).thenReturn(author);
        ResponseEntity<Author> response = authorController.create(author);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(author, response.getBody());
    }

    @Test
    public void deleteAuthor() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Alexandr")
                .surname("Pushkin")
                .birthDate(LocalDate.of(1799, Month.JUNE, 6))
                .build();
        doNothing().when(authorService).deleteAuthor(anyLong());
        ResponseEntity<String> response = authorController.delete(1L);
        verify(authorService).deleteAuthor(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Deleted", response.getBody());
    }

    @Test
    public void testFindByName() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Alexandr")
                .surname("Pushkin")
                .birthDate(LocalDate.of(1799, Month.JUNE, 6))
                .build();
        when(authorService.findAuthorByName(eq("Alexandr"), any(Pageable.class)))
                .thenReturn(Collections.singletonList(author));
        ResponseEntity<List<Author>> response = authorController.findByName("Alexandr", 0, 1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(author, response.getBody().get(0));

    }

    @Test
    public void testFindBySurname() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Alexandr")
                .surname("Pushkin")
                .birthDate(LocalDate.of(1799, Month.JUNE, 6))
                .build();
        when(authorService.findAuthorBySurname(eq("Pushkin"), any(Pageable.class)))
                .thenReturn(Collections.singletonList(author));
        ResponseEntity<List<Author>> response = authorController.findBySurname("Pushkin", 0, 1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(author, response.getBody().get(0));
    }

    @Test
    public void testUpdateAuthor() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder()
                .id(1L)
                .name("Alexandr")
                .surname("Pushkin")
                .birthDate(LocalDate.of(1799, Month.JUNE, 6))
                .build();
        doNothing().when(authorService).updateAuthor(eq(1L), any(Author.class));
        ResponseEntity<String> response = authorController.update(1L, author);
        verify(authorService).updateAuthor(1L, author);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated", response.getBody());
    }
}
