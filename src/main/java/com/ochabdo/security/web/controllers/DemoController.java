package com.ochabdo.security.web.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class DemoController {

    @GetMapping("/hy")
    public String display() {
        return "hy guys!";
    }
    
    
}
