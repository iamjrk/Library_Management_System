package com.example.Library_Management_System.Models;

import com.example.Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private Integer noOfBookIssued;

    //Foreign Key to be added
    //---------------------connections with different model/entity-----------------

    //Creating connection between parent(Student) & Child(Library Card)
    @OneToOne
    @JoinColumn
    private Student student; //this is foreign key i.e pk of Student table

    //Connection with Transactions Entity ( LibraryCard -> Parent Class & Transactions -> Child Class ) Bidirectional mapping
    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    private List<Transactions> transactionsList = new ArrayList<>();
}
