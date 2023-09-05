package com.example.Library_Management_System.CustomExceptions;

public class BookIsNotFromLibrary extends RuntimeException
{
    public BookIsNotFromLibrary(String message) {
        super(message);
    }
}
