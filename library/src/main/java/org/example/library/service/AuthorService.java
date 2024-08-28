package org.example.library.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.library.entity.Author;
import org.example.library.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        Author authorToSave= Author.builder()
                .name(author.getName())
                .surname(author.getSurname())
                .birthDate(author.getBirthDate())
                .build();
        authorRepository.save(author);
        return author;
    }

    public List<Author> findAuthorByName(String name, Pageable pageable) {
        Page<Author> list = authorRepository.findByName(name, pageable);
        return list.getContent();
    }

    public List<Author> findAuthorBySurname(String surname, Pageable pageable) {
        Page<Author> list = authorRepository.findBySurname(surname, pageable);
        return list.getContent();
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public void updateAuthor(Long id,Author author) {
        Author oldAuthor = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Author not found"));
        oldAuthor.setName(author.getName());
        oldAuthor.setSurname(author.getSurname());
        oldAuthor.setBirthDate(author.getBirthDate());
        authorRepository.save(oldAuthor);
    }
}
