package com.pial.book_club_organizer.service;

import com.pial.book_club_organizer.exception.BookAlreadyExistsException;
import com.pial.book_club_organizer.exception.BookNotFoundException;
import com.pial.book_club_organizer.exception.ReaderNotFoundException;
import com.pial.book_club_organizer.model.Book;
import com.pial.book_club_organizer.model.Reader;
import com.pial.book_club_organizer.repository.BookRepository;
import com.pial.book_club_organizer.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Book not found!"));
    }

    public void addBook(Book book) throws BookAlreadyExistsException {
        if (bookRepository.existsByTitle(book.getTitle())) {
            throw new BookAlreadyExistsException("Book already exists!");
        }
        bookRepository.save(book);
    }

    public void updateBook(Book book) throws BookNotFoundException {
        if (!bookRepository.existsById(book.getId())) {
            throw new BookNotFoundException("Book not found!");
        }
        bookRepository.saveAndFlush(book);
    }


//    public void deleteBookById(Integer id) throws BookNotFoundException {
//        if (!bookRepository.existsById(id)) {
//            throw new BookNotFoundException("Book not found!");
//        }
//        bookRepository.deleteById(id);
//    }
    public void deleteBookById(Integer bookId) throws BookNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            List<Reader> readersWithBook = readerRepository.findByBookListContaining(book);

            for (Reader reader : readersWithBook) {
                reader.getBookList().remove(book);
                readerRepository.save(reader);
            }
            bookRepository.delete(book);
        } else {
            throw new BookNotFoundException("Book not found!");
        }
    }

    public List<Book> findByTitleContainingOrAuthorContainingOrGenreContaining(String query, String query1, String query2) {
        return bookRepository.findByTitleContainingOrAuthorContainingOrGenreContaining(query,query1,query2);
    }

    public void removeBookFromReader(Integer readerId, Integer bookId) throws ReaderNotFoundException, BookNotFoundException {
        Optional<Reader> readerOptional = readerRepository.findById(readerId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (readerOptional.isPresent() && bookOptional.isPresent()) {
            Reader reader = readerOptional.get();
            Book book = bookOptional.get();

            if (reader.getBookList().contains(book)) {
                reader.getBookList().remove(book);
                readerRepository.save(reader);
            } else {
                throw new BookNotFoundException("Book not found in the reader's book list!");
            }
        } else {
            throw new ReaderNotFoundException("Reader or Book not found!");
        }
    }
}
