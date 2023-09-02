package com.example.Library_Management_System.RequestDto;

import com.example.Library_Management_System.Enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddBookRequestDto
{
    private String title;
    private Boolean isAvailable;
    private Genre genre;
    private Date publicationDate;
    private Integer priceOfBook;

    private Integer authorId;
}
