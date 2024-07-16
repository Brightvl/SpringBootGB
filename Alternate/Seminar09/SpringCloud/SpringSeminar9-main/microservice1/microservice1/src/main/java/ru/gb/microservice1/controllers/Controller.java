package ru.gb.microservice1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceOne")
public class Controller {
    @GetMapping("/Hello")
    public String hellow(){
        return "Hello form microservice 1";
    }
}
