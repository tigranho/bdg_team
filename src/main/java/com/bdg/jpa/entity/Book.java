package com.bdg.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Data


public class Book {

      @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "titleee")
    private String title;
    @OneToOne
    private Author author;
    int pageCount;
    private LocalDate publishDate;

public Book(String title, Author author, int pageCount, LocalDate publishDate){
    this.title = title;
    this.author = author;
    this.pageCount = pageCount;
    this.publishDate = publishDate;
}

    public Book() {

    }
}
