package com.ochabdo.security.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ochabdo.security.business.services.AuthenticationService;
import com.ochabdo.security.web.dto.AuthenticationRequest;
import com.ochabdo.security.web.dto.AuthenticationResponse;
import com.ochabdo.security.web.dto.RegisterRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService ;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest ) {
        return new ResponseEntity<AuthenticationResponse>(this.authenticationService.register(registerRequest), HttpStatus.CREATED);
    }


    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<AuthenticationResponse>(this.authenticationService.authenticate(authenticationRequest), HttpStatus.OK);
    }
    
    
    
}
