package org.example.librarywithjdbc.repository;

import lombok.RequiredArgsConstructor;
import org.example.librarywithjdbc.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> bookRowMapper = new RowMapper<Book>() {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPublicationYear(rs.getInt("publicationYear"));
            return book;
        }
    };

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO books (title, author, publicationYear) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public Optional<Book> findByTitle(String title) {
        return jdbcTemplate.query("SELECT * FROM books WHERE title = ?", new Object[]{title}, bookRowMapper)
                .stream().findFirst();
    }

    public Optional<Book> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id = ?", new Object[]{id}, bookRowMapper)
                .stream().findFirst();
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    public void update(Long id,Book book) {
        jdbcTemplate.update("UPDATE books SET title = ?, author = ?, publicationYear =? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getPublicationYear(), id);
    }
}
