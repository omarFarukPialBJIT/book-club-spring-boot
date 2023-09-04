package com.pial.book_club_organizer.repository;

import com.pial.book_club_organizer.model.Book;
import com.pial.book_club_organizer.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    boolean existsByEmail(String email);

    List<Reader> findByNameContainingOrDistrictContainingOrEmailContaining(String query, String query1, String query2);

    List<Reader> findByBookListContaining(Book book);
}
