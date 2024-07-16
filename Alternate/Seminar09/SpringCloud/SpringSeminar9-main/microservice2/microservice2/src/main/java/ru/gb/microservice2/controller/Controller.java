package ru.gb.microservice2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serviceTwo")
public class Controller {
    @GetMapping("/Hello")
    public String hellow(){
        return "Hello form microservice 2";
    }
}
