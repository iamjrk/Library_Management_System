package com.example.Library_Management_System.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String name;
    @Column(unique = true)
    private String emailId;

    private Integer age;
    private String penName;

    //---------------------connections with different model/entity-----------------
    //Connection with Book entity (Author -> Parent Class & Book -> Child class) bidirectional mapping
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> bookList=new ArrayList<>();
}
