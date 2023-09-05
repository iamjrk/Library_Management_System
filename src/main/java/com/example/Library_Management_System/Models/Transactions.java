package com.example.Library_Management_System.Models;

import com.example.Library_Management_System.Enums.TransactionStatus;
import com.example.Library_Management_System.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Entity
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transactions
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @CreationTimestamp //Time will be created automatically when row is inserted
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Integer fineAmount;

    public Transactions(TransactionType transactionType, TransactionStatus transactionStatus, Integer fineAmount) {
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.fineAmount = fineAmount;
    }

    //---------------------connections with different model/entity-----------------

    //Connection with Book entity
    @ManyToOne
    @JoinColumn
    private Book book;

    //Connection with LibraryCard entity
    @ManyToOne
    @JoinColumn
    private LibraryCard libraryCard;
}
