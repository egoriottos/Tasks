package org.example.library.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.library.entity.Book;
import org.example.library.repository.AuthorRepository;
import org.example.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    public Book createBook(Book book) {
        Book savedBook = Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
        bookRepository.save(book);
        return savedBook;
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Book> findAllBooks(Pageable pageable) {
        Page<Book> list = bookRepository.findAll(pageable);
        return list.getContent();
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public void updateBook(Long id, Book book, Long authorId) {
        Book bookToUpdate = findBookById(id);
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(authorRepository.findById(authorId).orElseThrow(EntityNotFoundException::new));
        bookRepository.save(bookToUpdate);
    }
}
