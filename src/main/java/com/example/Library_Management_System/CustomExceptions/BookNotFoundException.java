package com.example.Library_Management_System.CustomExceptions;

public class BookNotFoundException extends Exception
{
    public BookNotFoundException(String message)
    {
        super(message);
    }
}
