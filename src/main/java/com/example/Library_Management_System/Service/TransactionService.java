package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.CustomExceptions.*;
import com.example.Library_Management_System.Enums.CardStatus;
import com.example.Library_Management_System.Enums.TransactionStatus;
import com.example.Library_Management_System.Enums.TransactionType;
import com.example.Library_Management_System.Models.Book;
import com.example.Library_Management_System.Models.LibraryCard;
import com.example.Library_Management_System.Models.Transactions;
import com.example.Library_Management_System.Repositories.BookRepository;
import com.example.Library_Management_System.Repositories.LibraryCardRepository;
import com.example.Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService
{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Value("${book.maxLimit}")
    private Integer maxBookLimit;
    public String issueBook(Integer bookId, Integer cardId) throws Exception
    {
        //Initial phase of any transaction
        Transactions transactions = new Transactions(TransactionType.ISSUE, TransactionStatus.PENDING,0);

        //Book related exception handling

        Optional<Book>optionalBook=bookRepository.findById(bookId);
        if(!optionalBook.isPresent())
        {
            throw new BookNotFoundException("Book Id is incorrect");
        }
        Book book = optionalBook.get();
        if(book.isAvailable()==false)
        {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new BookNotAvailable("Book is not Available");
        }

        //Card related exception handling
        Optional<LibraryCard>optionalLibraryCard = libraryCardRepository.findById(cardId);
        if(!optionalLibraryCard.isPresent())
        {
            throw new CardNotAvailable("Card Id is incorrect");
        }
        LibraryCard card=optionalLibraryCard.get();
        if(card.getCardStatus().equals(CardStatus.NEW))
        {
            card.setCardStatus(CardStatus.ACTIVE);
        }
        if(!card.getCardStatus().equals(CardStatus.ACTIVE))
        {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new CardStatusIsNotRight("Card Status Is Not Right");
        }

        if(card.getNoOfBookIssued()==maxBookLimit)
        {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new BookIssueLimitExceeds(" Book Issue Limit is Exceeds, more books cannot be issued");
        }
        //All invalid / failed cases are over

        //for successful transaction
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setAvailable(false); //assuming only one book is present
        card.setNoOfBookIssued(card.getNoOfBookIssued()+1);

        //we need to do unidirectional mapping (mandatory step)--->
        transactions.setBook(book);
        transactions.setLibraryCard(card);

//        //since it has bidirectional mapping
//        // for book class (Parent)
//        List<Transactions>transactionsList = book.getTransactionsList();
//        transactionsList.add(transactions);
//        book.setTransactionsList(transactionsList);
//
//        //for LibraryCard Class (parent)
//        List<Transactions>cardTransactionsList = card.getTransactionsList();
//        cardTransactionsList.add(transactions);
//        card.setTransactionsList(cardTransactionsList);

        //save both parent class

        // //this will create two trxns rows simultaneously coz pk is unavailable
        //bookRepository.save(book);
        //libraryCardRepository.save(card);

        //this code better then above (87 - 102)
        Transactions newTransactionToGetPK = transactionRepository.save(transactions);
        //after saving , getting the trxn obj which contains PK

        //since it has bidirectional mapping
        // for book class (Parent)
        List<Transactions>transactionsList = book.getTransactionsList();
        transactionsList.add(newTransactionToGetPK);
        book.setTransactionsList(transactionsList);

        //for LibraryCard Class (parent)
        List<Transactions>cardTransactionsList = card.getTransactionsList();
        cardTransactionsList.add(newTransactionToGetPK);
        card.setTransactionsList(cardTransactionsList);

        //save both parent class
        //Now there will be only one trxn row even after saving two parent
        bookRepository.save(book);
        libraryCardRepository.save(card);

        return "Transaction is SUCCESSFUL";
    }
}
