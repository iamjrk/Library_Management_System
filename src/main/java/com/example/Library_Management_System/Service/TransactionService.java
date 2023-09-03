package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.CustomExceptions.*;
import com.example.Library_Management_System.Enums.CardStatus;
import com.example.Library_Management_System.Models.Book;
import com.example.Library_Management_System.Models.LibraryCard;
import com.example.Library_Management_System.Repositories.BookRepository;
import com.example.Library_Management_System.Repositories.LibraryCardRepository;
import com.example.Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        //Book related exception handling

        Optional<Book>optionalBook=bookRepository.findById(bookId);
        if(!optionalBook.isPresent())
        {
            throw new BookNotFoundException("Book Id is incorrect");
        }
        Book book = optionalBook.get();
        if(book.isAvailable()==false)
        {
            throw new BookNotAvailable("Book is not Available");
        }

        //Card related exception handling
        Optional<LibraryCard>optionalLibraryCard = libraryCardRepository.findById(cardId);
        if(!optionalLibraryCard.isPresent())
        {
            throw new CardNotAvailable("Card Id is incorrect");
        }
        LibraryCard card=optionalLibraryCard.get();
        if(!card.getCardStatus().equals(CardStatus.ACTIVE))
        {
            throw new CardStatusIsNotRight("Card Status Is Not Right");
        }

        if(card.getNoOfBookIssued()==maxBookLimit)
        {
            throw new BookIssueLimitExceeds(" Book Issue Limit is Exceeds, more books cannot be issued");
        }

        return "";
    }
}
