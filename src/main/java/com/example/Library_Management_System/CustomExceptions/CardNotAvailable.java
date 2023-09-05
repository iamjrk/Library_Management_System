package com.example.Library_Management_System.CustomExceptions;

public class CardNotAvailable extends RuntimeException
{
    public CardNotAvailable(String message)
    {
        super(message);
    }
}
