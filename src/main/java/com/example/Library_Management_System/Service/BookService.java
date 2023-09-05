package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Enums.Genre;
import com.example.Library_Management_System.Models.Author;
import com.example.Library_Management_System.Models.Book;
import com.example.Library_Management_System.Repositories.AuthorRepository;
import com.example.Library_Management_System.Repositories.BookRepository;
import com.example.Library_Management_System.RequestDto.AddBookRequestDto;
import com.example.Library_Management_System.ResponseDto.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    public String addBook(AddBookRequestDto requestDto) throws Exception
    {
        //Validation
        //AuthorId should be valid
        Optional<Author>optionalAuthor=authorRepository.findById(requestDto.getAuthorId());

        if(!optionalAuthor.isPresent())
        {
            throw new Exception("Author Id Entered is incorrect!!!");
        }
        Author author = optionalAuthor.get();

        Book book=new Book(requestDto.getTitle(), requestDto.getIsAvailable(),
                requestDto.getGenre(),requestDto.getPublicationDate(),requestDto.getPriceOfBook());
        //Entities will go into the db & entities will come out from db
        //Got the Book Object;
        //Set FK Variables;

        //Since its a bidirectional : need to set both in child & parent class

        //set parent entity in child class
        book.setAuthor(author);

        //set child entity in parent class
        List<Book>bookList=author.getBookList();
        bookList.add(book);

        author.setBookList(bookList);

        //since its bidirectional: save only parent, child will be automatically be saved
        authorRepository.save(author);
        return "Book has been Added successfully";
    }

    public Integer countBooksOnGenre(Genre genre)
    {
        List<Book>bookList=bookRepository.findAll();

        List<Book>genreBook=new ArrayList<>();

        for (int i=0;i<bookList.size();i++)
        {
            Book book=bookList.get(i);

            if(book.getGenre().equals(genre))
            {
                genreBook.add(book);
            }
        }
        return genreBook.size();
    }

    public List<BookResponseDto> getBookListByGenre(Genre genre)
    {
        List<Book>bookList=bookRepository.findBooksByGenre(genre);

        //stores required answer
        List<BookResponseDto> responseDtoList = new ArrayList<>();

        for (Book book:bookList)
        {
            BookResponseDto bookResponseDto =new BookResponseDto(book.getTitle(),
                    book.getIsAvailable(),book.getGenre(),book.getPublicationDate(),book.getPriceOfBook(),book.getAuthor().getName());

            responseDtoList.add(bookResponseDto);
        }
        return responseDtoList;
    }
}
