package com.example.Library_Management_System.CustomExceptions;

public class BookNotAvailable extends Exception
{
    public BookNotAvailable(String message)
    {
        super(message);
    }
}
