package org.example.library.controller;

import lombok.RequiredArgsConstructor;
import org.example.library.entity.Book;
import org.example.library.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> findBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> findAll(@RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "5") Integer size) {
        return ResponseEntity.ok(bookService.findAllBooks(PageRequest.of(page, size)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Deleted");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book,@RequestParam Long authorId) {
        bookService.updateBook(id, book, authorId);
        return ResponseEntity.ok("Updated");
    }
}
