package com.ochabdo.security.business.services;

import com.ochabdo.security.web.dto.AuthenticationRequest;
import com.ochabdo.security.web.dto.AuthenticationResponse;
import com.ochabdo.security.web.dto.RegisterRequest;

public interface AuthenticationService {
    
    AuthenticationResponse register(RegisterRequest registerRequest) ;
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
