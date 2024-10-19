package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/slow-endpoint")
    public ResponseEntity<List<Integer>> slowEndpoint() {
        // Simulate a slow process by calculating large prime numbers inefficiently
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; primes.size() < 10000; i++) {
            if (isPrimeSlow(i)) {
                primes.add(i);
            }
        }
        return ResponseEntity.ok(primes);
    }

    // Inefficient prime number check method (slow)
    private boolean isPrimeSlow(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; i++) { // Brute force from 2 to n-1
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @GetMapping("/fast-endpoint")
    public ResponseEntity<List<Integer>> fastEndpoint() {
        // Simulate a fast process by calculating large prime numbers efficiently
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; primes.size() < 10000; i++) {
            if (isPrimeFast(i)) {
                primes.add(i);
            }
        }
        return ResponseEntity.ok(primes);
    }

    // Optimized prime number check method (fast)
    private boolean isPrimeFast(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2 || number == 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= number; i += 6) { // Check up to square root of n
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}

