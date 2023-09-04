package com.pial.book_club_organizer.controller;


import com.pial.book_club_organizer.exception.BookAlreadyExistsException;
import com.pial.book_club_organizer.exception.BookNotFoundException;
import com.pial.book_club_organizer.exception.ReaderAlreadyExistsException;
import com.pial.book_club_organizer.exception.ReaderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<?> bookNotFoundException() {
        return new ResponseEntity<>(new BookNotFoundException("Book not found!").getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ReaderNotFoundException.class})
    public ResponseEntity<?> readerNotFoundException() {
        return new ResponseEntity<>(new ReaderNotFoundException("Reader not found!").getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BookAlreadyExistsException.class})
    public ResponseEntity<?> bookAlreadyExistsException() {
        return new ResponseEntity<>(new BookAlreadyExistsException("Book already exists!").getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ReaderAlreadyExistsException.class})
    public ResponseEntity<?> readerAlreadyExistsException() {
        return new ResponseEntity<>(new ReaderAlreadyExistsException("Reader already exists! Use another e-mail!").getMessage(), HttpStatus.BAD_REQUEST);
    }



}
