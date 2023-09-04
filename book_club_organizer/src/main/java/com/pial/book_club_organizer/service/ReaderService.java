package com.pial.book_club_organizer.service;

import com.pial.book_club_organizer.exception.ReaderAlreadyExistsException;
import com.pial.book_club_organizer.exception.ReaderNotFoundException;
import com.pial.book_club_organizer.model.Book;
import com.pial.book_club_organizer.model.Reader;
import com.pial.book_club_organizer.repository.BookRepository;
import com.pial.book_club_organizer.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;


    public List<Reader> getAllReader() {
        return readerRepository.findAll();
    }



    public Reader getReaderById(Integer id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(()-> new ReaderNotFoundException("Reader not found!"));
    }




    public Book createOrGetBookByName(String title, String author, String genre) {
        Optional<Book> existingBook = bookRepository.findByTitle(title);

        if (existingBook.isPresent()) {
            return existingBook.get();
        } else {
            Book newBook = new Book(title, author, genre);
            return bookRepository.save(newBook);
        }
    }


    public void addReader(Reader reader) throws ReaderAlreadyExistsException {
        if (readerRepository.existsByEmail(reader.getEmail())) {
            throw new ReaderAlreadyExistsException("Reader already exists! Use another e-mail!");
        }
        if (reader.getBookList() == null) {
            reader.setBookList(new ArrayList<>());
        }
        List<Book> books = new ArrayList<>();
        for (Book book : reader.getBookList()) {
            Book existingOrNewBook = createOrGetBookByName(book.getTitle(), book.getAuthor(), book.getGenre());
            books.add(existingOrNewBook);
        }
        reader.setBookList(books);
        readerRepository.save(reader);
    }

    public void updateReader(Reader reader) throws ReaderNotFoundException {
        if (!readerRepository.existsById(reader.getId())) throw new ReaderNotFoundException("Reader not found!");
        readerRepository.saveAndFlush(reader);
    }


    public void deleteReaderById(Integer id) throws ReaderNotFoundException {
        if (!readerRepository.existsById(id)) throw new ReaderNotFoundException("Reader not found!");

        readerRepository.deleteById(id);
    }



    public List<Reader> findByNameContainingOrDistrictContainingOrEmailContaining(String query, String query1, String query2) {
        return readerRepository.findByNameContainingOrDistrictContainingOrEmailContaining(query,query1,query2);
    }
}
