package com.example.Library_Management_System.Controller;

import com.example.Library_Management_System.Enums.Department;
import com.example.Library_Management_System.Models.Student;
import com.example.Library_Management_System.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j //to get the log error message
public class StudentController
{
    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student)
    {
        try
        {
            String result = studentService.addStudent(student);
            return new ResponseEntity(result,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            log.error("Student not added successfully..."+e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/findDeptById")
    public ResponseEntity findDepartmentById(@RequestParam("Id")Integer Id)
    {
        try
        {
            Department department=studentService.getDepartmentById(Id);
            return new ResponseEntity(department,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            log.error("Department Not Found / Invalid Request "+e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
