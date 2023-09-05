package com.example.Library_Management_System.CustomExceptions;

public class BookNotAvailable extends RuntimeException
{
    public BookNotAvailable(String message)
    {
        super(message);
    }
}
