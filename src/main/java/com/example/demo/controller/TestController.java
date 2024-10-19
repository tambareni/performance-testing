package com.example.demo.controller;


import com.example.demo.pojo.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRepository personRepository1;

    public TestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Slow endpoint: Inefficient SQL + Slow processing
    @GetMapping("/slow")
    public ResponseEntity<List<Person>> slowEndpoint() {
        List<Person> people = new ArrayList<>();
        Person person1 = new Person();
        //TO-DO:
        // Inefficient SQL: Fetch records one by one
        List<Long> ids = personRepository.findAllIds();
        for (Long id : ids) {
            people.add(personRepository.findById(id).orElse(null));
        }

        // Inefficient processing: Find prime numbers for each person ID (inefficient algorithm)
        for (Person person : people) {
            findNthPrimeSlow(person.getId().intValue());
        }

        return ResponseEntity.ok(people);
    }

    // Fast endpoint: Optimized SQL + Fast processing
    @GetMapping("/fast")
    public ResponseEntity<List<Person>> fastEndpoint() {
        // Optimized SQL: Fetch all records in one go
        List<Person> people = personRepository.findAll();

        // Optimized processing: Efficient prime number calculation for each person ID
        for (Person person : people) {
            findNthPrimeFast(person.getId().intValue());
        }

        return ResponseEntity.ok(people);
    }

    // Optimized prime number algorithm (more efficient)
    private int findNthPrimeFast(int n) {
        if (n < 1) return 0;

        List<Integer> primes = new ArrayList<>();
        int number = 2; // Starting from the first prime number

        while (primes.size() < n) {
            boolean isPrime = true;
            for (int prime : primes) {
                if (prime * prime > number) break; // Optimization: No need to check beyond sqrt(number)
                if (number % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(number);
            }
            number++;
        }

        return primes.get(n - 1);
    }

    // Slow prime number algorithm (inefficient)
    private int findNthPrimeSlow(int n) {
        int num = 1, count = 0, i;
        while (count < n) {
            num++;
            for (i = 2; i <= num; i++) {
                if (num % i == 0) {
                    break;
                }
            }
            if (i == num) {
                count++;
            }
        }
        return num;
    }


}
