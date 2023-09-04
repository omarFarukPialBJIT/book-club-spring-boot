package com.pial.book_club_organizer.exception;

public class BookAlreadyExistsException extends Exception{
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
