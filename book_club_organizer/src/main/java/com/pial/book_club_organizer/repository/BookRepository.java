package com.pial.book_club_organizer.repository;

import com.pial.book_club_organizer.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByTitle(String title);

    Optional<Book> findByTitle(String title);

    List<Book> findByTitleContainingOrAuthorContainingOrGenreContaining(String query, String query1, String query2);
}
