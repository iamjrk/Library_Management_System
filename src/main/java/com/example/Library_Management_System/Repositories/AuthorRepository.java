package com.example.Library_Management_System.Repositories;

import com.example.Library_Management_System.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer>
{
    @Query(value = "select * from author where age >=:givenAge",nativeQuery = true)
    List<Author> findAuthorGreaterThanAge(Integer givenAge);

}
