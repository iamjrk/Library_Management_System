package com.example.Library_Management_System.CustomExceptions;

public class CardStatusIsNotRight extends RuntimeException
{
    public CardStatusIsNotRight(String message)
    {
        super(message);
    }
}
