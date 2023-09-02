package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Enums.Department;
import com.example.Library_Management_System.Models.Student;
import com.example.Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService
{
    @Autowired
    private StudentRepository studentRepository;
    public String addStudent(Student student)throws Exception
    {
        //validation
//        //it will check in db if rollNo already exists throws exception
//        if(studentRepository.existsById(student.getRollNo()))
//        {
//            throw new Exception("Id Should not be sent as a parameter.");
//        }

        //it will check whether the object contains any value for rollNo?
        // if contains any value it will throw an exception, it will not check in db
        if(student.getRollNo()!=null)
        {
            throw new Exception("Id Should not be sent as a parameter.");
        }
        studentRepository.save(student);
        return "Student has been added Successfully";
    }

    public Department getDepartmentById(Integer rollNo)throws Exception
    {
        Optional<Student> optionalStudent=studentRepository.findById(rollNo);

        if(!optionalStudent.isPresent())
        {
            throw new Exception("Roll No entered is Invalid!!!");
        }
        Student student=optionalStudent.get();
        return student.getDepartment();
    }
}
