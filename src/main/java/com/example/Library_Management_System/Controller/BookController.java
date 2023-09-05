package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Enums.Genre;
import com.example.Library_Management_System.RequestDto.AddBookRequestDto;
import com.example.Library_Management_System.ResponseDto.BookResponseDto;
import com.example.Library_Management_System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController
{
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequestDto addBookRequestDto)
    {
        try
        {
            String result=bookService.addBook(addBookRequestDto);
            return new ResponseEntity(result,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity("Book has not been added!!! reason: "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/countBooksOnGenre")
    public ResponseEntity countBooksOnGenre(@RequestParam("genre")Genre genre)
    {
        try
        {
            Integer count=bookService.countBooksOnGenre(genre);
            return new ResponseEntity(count,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity("Something went wrong!!!! error:"+e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/getBookListByGenre")
    public ResponseEntity getBookListByGenre(@RequestParam("genre")Genre genre)
    {
        try {
            List<BookResponseDto>responseDtoList=bookService.getBookListByGenre(genre);
            return new ResponseEntity(responseDtoList,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
