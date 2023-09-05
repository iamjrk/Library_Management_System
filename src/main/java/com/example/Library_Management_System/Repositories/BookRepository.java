package com.example.Library_Management_System.Repositories;

import com.example.Library_Management_System.Enums.Genre;
import com.example.Library_Management_System.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>
{
    List<Book>findBooksByGenre(Genre genre);
}
