package com.ochabdo.security.business.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ochabdo.security.business.services.AuthenticationService;
import com.ochabdo.security.business.services.JwtService;
import com.ochabdo.security.dao.Repositories.UserRepository;
import com.ochabdo.security.dao.entities.User;
import com.ochabdo.security.web.dto.AuthenticationRequest;
import com.ochabdo.security.web.dto.AuthenticationResponse;
import com.ochabdo.security.web.dto.RegisterRequest;
import com.ochabdo.security.web.dto.Role;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository ;

    /*@Autowired
    private UserService userService ;*/

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private JwtService jwtService ;

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = new User(null,registerRequest.getFirstname(), 
                            registerRequest.getLastname(),
                            registerRequest.getEmail(),
                            this.passwordEncoder.encode(registerRequest.getPassword()),
                            Role.STUDIANT);
        this.userRepository.save(user);
        AuthenticationResponse auth = new AuthenticationResponse(this.jwtService.generateToken(user));
        return auth ;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        User user = this.userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        AuthenticationResponse auth = new AuthenticationResponse(this.jwtService.generateToken(user));
        return auth ;
        
    }

    
    
}
