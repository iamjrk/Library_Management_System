package com.example.Library_Management_System.Service;

import com.example.Library_Management_System.Enums.CardStatus;
import com.example.Library_Management_System.Models.LibraryCard;
import com.example.Library_Management_System.Models.Student;
import com.example.Library_Management_System.Repositories.LibraryCardRepository;
import com.example.Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryCardService {
    @Autowired
    LibraryCardRepository libraryCardRepository;
    @Autowired
    StudentRepository studentRepository;
    public String createCard(LibraryCard libraryCard)  throws Exception
    {
        if(libraryCard.getCardNo()!=null)
        {
            throw new Exception("Card No should not be sent as a parameter!!!");
        }
        libraryCardRepository.save(libraryCard);
        return "Library Card has created successfully & added to database!!!";
    }

    public String linkToStudent(Integer cardId, Integer rollNo) throws Exception
    {
        //validation
        //1.Student should exist
        if(!studentRepository.existsById(rollNo))//if rollNo does not exists
        {
            throw new Exception("Student RollNo is Invalid");
        }
        //2.Card Should also exist
        if(!libraryCardRepository.existsById(cardId))//if cardId does not exists
        {
            throw new Exception("Library Card Id entered is Invalid");
        }

        Optional<Student>optionalStudent=studentRepository.findById(rollNo);
        Student student=optionalStudent.get();

        Optional<LibraryCard>optionalLibraryCard=libraryCardRepository.findById(cardId);
        LibraryCard libraryCard=optionalLibraryCard.get();

        //Update foreign key variable
        //Set Student Object in library class
        libraryCard.setStudent(student);

        //Set libraryCard Object in Student class
        student.setLibraryCard(libraryCard);

        //Since Student & libraryCard have bidirectional mapping having CascadeType.All
        //we just have to save for Parent class i.e Student class, child class will automatically be saved
        studentRepository.save(student);

        //above approach is preferred but instead we can also use
        //studentRepository.save(student);
        //libraryCardRepository.save(libraryCard);

        return "Card has been linked successfully!!!";
    }

    public CardStatus getCardStatus(Integer studentId) throws Exception {
        try
        {
            Optional<Student>optionalStudent = studentRepository.findById(studentId);

            if(!optionalStudent.isPresent())
            {
                throw new Exception("Student Id is Invalid");
            }

            Student student = optionalStudent.get();

            LibraryCard libraryCard =student.getLibraryCard();

            CardStatus cardStatus=libraryCard.getCardStatus();

            return cardStatus;
        }
        catch (Exception e)
        {
            throw new Exception("Not Found");
        }

    }
}
