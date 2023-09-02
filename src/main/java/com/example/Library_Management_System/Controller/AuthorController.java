package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Models.Author;
import com.example.Library_Management_System.RequestDto.UpdateNameAndPenNameRequestDto;
import com.example.Library_Management_System.Service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController
{
    @Autowired
    private AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author)
    {
        try{
            String status=authorService.addAuthor(author);
            return new ResponseEntity(status, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            log.error("Author could not be added to the db!!! Reason: "+ e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/UpdateNameAndPenName")
//    public String updateNameAndPenName(@RequestParam("Id")Integer authorId,
//                                       @RequestParam("name")String name, @RequestParam("penName")String penName)
//    {
//        //This is also a way possible to update, but it has few drawbacks :
//        //1.endpoint has become long
//        //2. Exposed in URL itself
//    }
    public ResponseEntity updateNameAndPenName(@RequestBody UpdateNameAndPenNameRequestDto updateNameAndPenNameRequestDto)
    {
        try
        {
            String status = authorService.updateNameAndPenName(updateNameAndPenNameRequestDto);
            return new ResponseEntity(status,HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity("Author Id is Invalid!!! error: "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authorDetails")
    public ResponseEntity getAuthorDetails(@RequestParam("authorId") Integer authorId)
    {
        try
        {
            Author author=authorService.getAuthorDetails(authorId);
            return new ResponseEntity(author,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/bookTitlesOfAuthor")
    public ResponseEntity getBookTitlesOfAuthor(@RequestParam("authorId")Integer authorId)
    {
        try
        {
            List<String>bookTitles=authorService.getBookTitlesOfAuthor(authorId);
            if(bookTitles.size()==0)
            {
                return new ResponseEntity("Book List Not Found",HttpStatus.FOUND);
            }
            return new ResponseEntity(bookTitles,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
