package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Models.Student;
import com.example.Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;
    public String addStudent(Student student)
    {
        return "";
    }
}
