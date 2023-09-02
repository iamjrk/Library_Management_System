package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Enums.CardStatus;
import com.example.Library_Management_System.Models.LibraryCard;
import com.example.Library_Management_System.Service.LibraryCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libraryCard")
@Slf4j
public class LibraryCardController
{
    @Autowired
    LibraryCardService libraryCardService;
    @PostMapping("/createCard")
    public ResponseEntity createCard(@RequestBody LibraryCard libraryCard)
    {
        try
        {
            String status=libraryCardService.createCard(libraryCard);
            return new ResponseEntity<>(status, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/issueToStudent")
    public ResponseEntity issueToStudent(@RequestParam("cardId") Integer cardId,@RequestParam("rollNo")Integer rollNo)
    {
        try
        {
            String status= libraryCardService.linkToStudent(cardId,rollNo);
            return new ResponseEntity(status,HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            log.error("Error in associating Card to Student !!!"+e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/cardStatus")
    public ResponseEntity getCardStatus(@RequestParam("studentId") Integer studentId)
    {
        try
        {
            CardStatus cardStatus = libraryCardService.getCardStatus(studentId);
            return new ResponseEntity(cardStatus,HttpStatus.FOUND);
        }
        catch(Exception e)
        {
            String s=e.getMessage().toString();
            return new ResponseEntity(s+" !!"+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
