package com.example.Library_Management_System.CustomExceptions;

public class BookNotFoundException extends RuntimeException
{
    public BookNotFoundException(String message)
    {
        super(message);
    }
}
