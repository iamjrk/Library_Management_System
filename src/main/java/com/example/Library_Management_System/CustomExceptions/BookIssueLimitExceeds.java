package com.example.Library_Management_System.CustomExceptions;

public class BookIssueLimitExceeds extends RuntimeException
{
    public BookIssueLimitExceeds(String message)
    {
        super(message);
    }
}
