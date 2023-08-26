package com.example.Library_Management_System.Models;

import com.example.Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int cardNo;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private int noOfBookIssued;

    //Foreign Key to be added
    //Creating connection between parent(Student) & Child(Library Card)
    @OneToOne
    @JoinColumn
    private Student student; //this is foreign key i.e pk of Student table
}
