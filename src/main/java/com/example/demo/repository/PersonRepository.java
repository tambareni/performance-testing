package com.example.demo.repository;

import com.example.demo.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    // Fetch all person IDs (used in slow version)
    @Query("SELECT p.id FROM Person p")
    List<Long> findAllIds();

    // Fetch all persons (used in fast version)
    List<Person> findAll();
}
