package com.ochabdo.security.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/studiant")
public class StudiantController {

    @GetMapping()
    public String getMethod() {
        return "GET : Studiant";
    }

    @PostMapping()
    public String postMethod() {
        return "POST : Studiant" ;
    }
    
    @PutMapping()
    public String putMethod() {
        return "PUT : Studiant";
    }
    
}
