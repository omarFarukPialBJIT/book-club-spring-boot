package com.pial.book_club_organizer.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Component
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String district;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "book_reader_association",
            joinColumns = @JoinColumn(name = "reader_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
    )
    private List<Book> bookList;


    public Reader() {
    }

    public Reader(Integer id, String name, Integer age, String district, String email) {
        this.name = name;
        this.age = age;
        this.district = district;
        this.email = email;
        this.bookList = new ArrayList<Book>();
    }
    public Reader(String name, Integer age, String district, String email) {
        this.name = name;
        this.age = age;
        this.district = district;
        this.email = email;
        this.bookList = new ArrayList<Book>();
    }

    public Reader(Integer id, String name, Integer age, String district, String email, List<Book> bookList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.district = district;
        this.email = email;
        this.bookList = bookList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}


