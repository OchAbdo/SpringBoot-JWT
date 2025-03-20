package com.ochabdo.security.web.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;





@RestController()
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping()
    @PreAuthorize("hasAuthority('admin:read')")
    public String getMethodName() {
        return "GET : Admin";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:create')")
    public String postMethodName() {
        
        return "POST : Admin" ;
    }
    
    @PutMapping()
    @PreAuthorize("hasAuthority('admin:update')")
    public String putMethodName() {
        return "PUT : Admin";
    }
    
    
    
}
