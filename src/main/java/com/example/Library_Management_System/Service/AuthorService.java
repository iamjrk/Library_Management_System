package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Models.Author;
import com.example.Library_Management_System.Models.Book;
import com.example.Library_Management_System.Repositories.AuthorRepository;
import com.example.Library_Management_System.Repositories.BookRepository;
import com.example.Library_Management_System.RequestDto.UpdateNameAndPenNameRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    public String addAuthor(Author author) throws Exception
    {
        //Validation Checks
        if(author.getAuthorId()!=null)
        {
            throw new Exception("Author Id should not be sent as a parameter!!!");
        }
        authorRepository.save(author);
        return "Author has been added successfully added to the db";
    }

    public String updateNameAndPenName(UpdateNameAndPenNameRequestDto requestDto) throws Exception
    {
        Optional<Author>authorOptional=authorRepository.findById(requestDto.getAuthorId());
        //validation
        if(!authorOptional.isPresent())
        {
            throw new Exception("Author Id is invalid, check Id again");
        }

        Author author=authorOptional.get();
        author.setName(requestDto.getNewName());
        author.setPenName(requestDto.getNewPenName());

        authorRepository.save(author);

        return "Author Name & PenName has been updated successfully";
    }

    public Author getAuthorDetails(Integer authorId) throws Exception
    {
        Optional<Author>optionalAuthor=authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent())
        {
            throw new Exception("Author Id is Invalid");
        }
        Author author = optionalAuthor.get();
        return author;
    }

    public List<String> getBookTitlesOfAuthor(Integer authorId) throws Exception
    {
        Optional<Author>optionalAuthor = authorRepository.findById(authorId);

        if(!optionalAuthor.isPresent())
        {
            throw new Exception("Author Id is invalid!!!");
        }

        Author author=optionalAuthor.get();
        List<Book>bookList=author.getBookList();

        List<String>bookTitles= new ArrayList<>();
        for(int i=0;i<bookList.size();i++)
        {
            Book book=bookList.get(i);
            bookTitles.add(book.getTitle());
        }
        return bookTitles;
    }

    public List<String> getAuthorGreaterThanAge(Integer givenAge)
    {
        List<Author>authorList=authorRepository.findAuthorGreaterThanAge(givenAge);

        List<String>list=new ArrayList<>();
        for (Author author:authorList)
        {
            list.add(author.getName());
        }
        return list;
    }
}
