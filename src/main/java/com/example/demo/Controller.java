package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/test")
    public String test() throws InterruptedException {
        sleep();
        return "Tested";
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(3000);
    }
}
