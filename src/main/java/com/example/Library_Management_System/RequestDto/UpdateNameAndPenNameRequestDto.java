package com.example.Library_Management_System.RequestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNameAndPenNameRequestDto
{
    private Integer authorId;
    private String newName;
    private String newPenName;
}
