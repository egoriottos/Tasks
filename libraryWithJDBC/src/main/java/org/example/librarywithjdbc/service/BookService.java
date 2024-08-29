package org.example.librarywithjdbc.service;

import lombok.RequiredArgsConstructor;
import org.example.librarywithjdbc.entity.Book;
import org.example.librarywithjdbc.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void createBook(Book book) {
        Book book1 = Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .build();
        bookRepository.save(book1);
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(null);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void updateBook(Long id,Book book) {
        Book book1 = findById(id);
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setPublicationYear(book.getPublicationYear());
        bookRepository.save(book1);
    }
}
