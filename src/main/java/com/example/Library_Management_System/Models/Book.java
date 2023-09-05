package com.example.Library_Management_System.Models;

import com.example.Library_Management_System.Enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    public Book(String title, boolean isAvailable, Genre genre, Date publicationDate, Integer priceOfBook) {
        this.title = title;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.priceOfBook = priceOfBook;
    }

    @Column(unique = true)
    private String title;

    //Assuming there is 1 piece of book in the library
    private boolean isAvailable;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Date publicationDate;
    private Integer priceOfBook;

    //---------------------connections with different model/entity-----------------

    //Connection with Author Entity
    @ManyToOne
    @JoinColumn
    @JsonIgnore //this will ignore author entity while returning the details of book object ** its for Api: getAuthorDetails
    private Author author;

    //Connection with Transactions Entity
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transactions> transactionsList = new ArrayList<>();
}
