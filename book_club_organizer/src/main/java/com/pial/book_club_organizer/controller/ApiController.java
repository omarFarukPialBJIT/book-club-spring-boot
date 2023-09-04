package com.pial.book_club_organizer.controller;


import com.pial.book_club_organizer.exception.BookAlreadyExistsException;
import com.pial.book_club_organizer.exception.BookNotFoundException;
import com.pial.book_club_organizer.exception.ReaderAlreadyExistsException;
import com.pial.book_club_organizer.exception.ReaderNotFoundException;
import com.pial.book_club_organizer.model.Book;
import com.pial.book_club_organizer.model.Reader;
import com.pial.book_club_organizer.service.BookService;
import com.pial.book_club_organizer.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;
    @GetMapping("book/all")
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> bookList = bookService.getAllBook();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
    @GetMapping("book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) throws BookNotFoundException {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("book/add")
    public ResponseEntity<Void> addBook(@RequestBody Book book) throws BookAlreadyExistsException {
        bookService.addBook(book);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("book/update")
    public ResponseEntity<Void> updateBook(@RequestBody Book book) throws BookNotFoundException  {
        bookService.updateBook(book);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) throws BookNotFoundException {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }



    @GetMapping("reader/all")
    public ResponseEntity<List<Reader>> getAllReader() {
        List<Reader> readerList = readerService.getAllReader();
        return new ResponseEntity<>(readerList, HttpStatus.OK);
    }
    @GetMapping("reader/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable Integer id) throws ReaderNotFoundException {
        Reader reader = readerService.getReaderById(id);
        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @PostMapping("reader/add")
    public ResponseEntity<Void> addReader(@RequestBody Reader reader) throws ReaderAlreadyExistsException {
        readerService.addReader(reader);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("reader/update")
    public ResponseEntity<Void> updateReader(@RequestBody Reader reader) throws ReaderNotFoundException {
        readerService.updateReader(reader);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("reader/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Integer id) throws ReaderNotFoundException {
        readerService.deleteReaderById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
