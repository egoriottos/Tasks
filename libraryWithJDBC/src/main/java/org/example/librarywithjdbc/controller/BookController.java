package org.example.librarywithjdbc.controller;

import lombok.RequiredArgsConstructor;
import org.example.librarywithjdbc.entity.Book;
import org.example.librarywithjdbc.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    /*
    CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publicationYear INT NOT NULL);
     */
    private final BookService bookService;

    @PostMapping("/create")
    public String createBook(@RequestBody Book book) {
         bookService.createBook(book);
         return "Book created";
    }

    @GetMapping("/title/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService.findBookByTitle(title);
    }

    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted";
    }

    @PutMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
        return "Book updated";
    }
}
