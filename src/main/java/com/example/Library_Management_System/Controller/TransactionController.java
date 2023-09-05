package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController
{
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("bookId") Integer bookId, @RequestParam("cardId")Integer cardId)
    {
        try
        {
            String result= transactionService.issueBook(bookId,cardId);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping("/returnBook")
    public ResponseEntity returnBook(@RequestParam("bookId") Integer bookId, @RequestParam("cardId")Integer cardId)
    {
        try
        {
            String result= transactionService.returnBook(bookId,cardId);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
