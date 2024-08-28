package org.example.library.controller;

import lombok.RequiredArgsConstructor;
import org.example.library.entity.Author;
import org.example.library.service.AuthorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author saveAuthor = authorService.createAuthor(author);
        return ResponseEntity.ok(saveAuthor);
    }

    @GetMapping("/find/by/name/{name}")
    public ResponseEntity<List<Author>> findByName(@PathVariable String name,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "5") Integer size) {
        return ResponseEntity.ok(authorService.findAuthorByName(name, PageRequest.of(page,size)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Deleted");
    }
    @GetMapping("/find/by/surname/{surname}")
    public ResponseEntity<List<Author>> findBySurname(@PathVariable String surname, @RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "5") Integer size) {
        return ResponseEntity.ok(authorService.findAuthorBySurname(surname, PageRequest.of(page,size)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody Author author) {
        authorService.updateAuthor(id, author);
        return ResponseEntity.ok("Updated");
    }
}
