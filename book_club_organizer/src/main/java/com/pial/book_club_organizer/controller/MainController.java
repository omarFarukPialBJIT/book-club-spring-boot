package com.pial.book_club_organizer.controller;


import com.pial.book_club_organizer.exception.BookAlreadyExistsException;
import com.pial.book_club_organizer.exception.BookNotFoundException;
import com.pial.book_club_organizer.exception.ReaderAlreadyExistsException;
import com.pial.book_club_organizer.exception.ReaderNotFoundException;
import com.pial.book_club_organizer.model.Book;
import com.pial.book_club_organizer.model.Reader;
import com.pial.book_club_organizer.repository.BookRepository;
import com.pial.book_club_organizer.repository.ReaderRepository;
import com.pial.book_club_organizer.service.BookService;
import com.pial.book_club_organizer.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;
    @GetMapping("/")
    public String home () {
        return "home";
    }

    @GetMapping("book/all")
    public String books(Model model) {
        List<Book> bookList = bookService.getAllBook();
        model.addAttribute("bookslist", bookList);
        return "bookshow";
    }


    @PostMapping("books/search")
    public String searchBooks(@RequestParam String query, Model model) {
        List<Book> matchingBooks;
        if (!query.isEmpty()) {
            matchingBooks = bookService.findByTitleContainingOrAuthorContainingOrGenreContaining(query, query, query);
        } else {
            matchingBooks = bookService.getAllBook();
            model.addAttribute("noResultsMessage", "Sorry, no book found!");
        }
        model.addAttribute("bookslist", matchingBooks);
        return "bookshow";
    }

    @GetMapping("book/add")
    public String addBook() {
        return "addbook";
    }

    @PostMapping("book/save")
    public String saveBook(@ModelAttribute Book book, Model model){
        try {
            bookService.addBook(book);
        } catch (BookAlreadyExistsException e) {
            model.addAttribute("error", "Book already exists!");
            return "addbook";
        }

        return "redirect:/book/all";
    }

    @GetMapping("book/edit/{id}")
    public String editBook(@PathVariable Integer id, Model model) throws BookNotFoundException {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "editbook";
    }

    @PostMapping("book/update")
    public String updateBook(@ModelAttribute Book book) throws BookNotFoundException {
        bookService.updateBook(book);
        return "redirect:/book/all";
    }

    @GetMapping("book/delete/{id}")
    public String deleteBook(@PathVariable Integer id) throws BookNotFoundException {
        bookService.deleteBookById(id);
        return "redirect:/book/all";
    }


    @GetMapping("reader/all")
    public String readers(Model model) {
        List<Reader> readerList = readerService.getAllReader();
        model.addAttribute("readerslist", readerList);
        return "readershow";
    }

    @GetMapping("reader/add")
    public String readerBook() {
        return "addreader";
    }

    @PostMapping("reader/save")
    public String saveReader(@ModelAttribute Reader reader, Model model) {
        try {
            readerService.addReader(reader);
        } catch (ReaderAlreadyExistsException e) {
            model.addAttribute("error", "Email already exists!");
            return "addreader";
        }
        return "redirect:/reader/all";
    }

    @PostMapping("readers/search")
    public String searchReaders(@RequestParam String query, Model model) {
        List<Reader> matchingReaders;
        if (!query.isEmpty()) {
            matchingReaders = readerService.findByNameContainingOrDistrictContainingOrEmailContaining(query, query, query);
        } else {
            matchingReaders = readerService.getAllReader();
        }
        model.addAttribute("readerslist", matchingReaders);
        return "readershow";
    }



    @GetMapping("reader/edit/{id}")
    public String editReader(@PathVariable Integer id, Model model) throws ReaderNotFoundException {
        Reader reader = readerService.getReaderById(id);
        model.addAttribute("reader", reader);
        return "editreader";
    }

    @PostMapping("reader/update")
    public String updateReader(@ModelAttribute Reader reader) throws ReaderNotFoundException {
        readerService.updateReader(reader);
        return "redirect:/reader/all";
    }

    @GetMapping("reader/delete/{id}")
    public String deleteReader(@PathVariable Integer id) throws ReaderNotFoundException {
        readerService.deleteReaderById(id);
        return "redirect:/reader/all";
    }


    @GetMapping("readerbook")
    public String readerbook(Model model) {
        List<Reader> readers = readerService.getAllReader();
        model.addAttribute("readerbooks", readers);
        return "readerbookshow";
    }


}
