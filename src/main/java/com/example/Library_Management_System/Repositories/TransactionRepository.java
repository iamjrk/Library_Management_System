package com.example.Library_Management_System.Repositories;

import com.example.Library_Management_System.Enums.TransactionStatus;
import com.example.Library_Management_System.Enums.TransactionType;
import com.example.Library_Management_System.Models.Book;
import com.example.Library_Management_System.Models.LibraryCard;
import com.example.Library_Management_System.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Integer>
{
    List<Transactions> findTransactionByBookAndLibraryCardAndTransactionStatusAndTransactionType(Book book, LibraryCard card, TransactionStatus transactionStatus, TransactionType transactionType);
}
