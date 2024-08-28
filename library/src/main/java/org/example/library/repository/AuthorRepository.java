package org.example.library.repository;

import org.example.library.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Page<Author> findByName(String firstName, Pageable pageable);
    Page<Author> findBySurname(String lastName, Pageable pageable);
}
