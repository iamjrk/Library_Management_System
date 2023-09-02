package com.example.Library_Management_System.Models;

import com.example.Library_Management_System.Enums.Department;
import com.example.Library_Management_System.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
    @Id //used for the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rollNo;
    private String name;
    private Integer age;
    //Gender and Department are user defined datatype, it is not understand by Mysql
    @Enumerated(value = EnumType.STRING) //this will help sql to understand about the value for gender
    private Gender gender; //This gender variable is of user defined datatype: this contains only 2 values
    @Enumerated(value = EnumType.STRING)
    private Department department;
    @Column(unique = true) //ensures there will be unique e-mail ids
    private String emailId;

    //---------------------connections with different model/entity-----------------

    //Connection with LibraryCard entity (Student -> Parent Class & LibraryCard -> Child Class) Bidirectional mapping
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
}
