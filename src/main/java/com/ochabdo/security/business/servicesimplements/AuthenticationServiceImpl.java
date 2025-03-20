package com.ochabdo.security.business.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ochabdo.security.business.services.AuthenticationService;
import com.ochabdo.security.business.services.JwtService;
import com.ochabdo.security.dao.Repositories.TokenRepository;
import com.ochabdo.security.dao.Repositories.UserRepository;
import com.ochabdo.security.dao.entities.Token;
import com.ochabdo.security.dao.entities.User;
import com.ochabdo.security.web.dto.AuthenticationRequest;
import com.ochabdo.security.web.dto.AuthenticationResponse;
import com.ochabdo.security.web.dto.RegisterRequest;
import com.ochabdo.security.web.dto.Role;
import com.ochabdo.security.web.dto.TypeToken;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {



    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private TokenRepository tokenRepository ;

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
        User user =  User.builder()
                        .firstname(registerRequest.getFirstname())
                        .lastname(registerRequest.getLastname())
                        .email(registerRequest.getEmail())
                        .password(this.passwordEncoder.encode(registerRequest.getPassword()))
                        .role(registerRequest.getRole())
                        .build() ;
        User u = this.userRepository.save(user);
        var jwt = this.jwtService.generateToken(u);
        Token token = Token.builder()
                            .tokenname(jwt)
                            .typetoken(TypeToken.BEARER)
                            .expired(false)
                            .revoked(false)
                            .user(u)
                            .build();
        this.tokenRepository.save(token);
        AuthenticationResponse auth = new AuthenticationResponse(jwt);
        return auth ;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        User user = this.userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwt = this.jwtService.generateToken(user);
        Token token = Token.builder()
                            .tokenname(jwt)
                            .typetoken(TypeToken.BEARER)
                            .expired(false)
                            .revoked(false)
                            .user(user)
                            .build();
        revokeAllUser(user);
        this.tokenRepository.save(token);
        AuthenticationResponse auth = new AuthenticationResponse(jwt);
        return auth ;
        
    }


    private void revokeAllUser(User user)
    {
        var tokens = this.tokenRepository.findAllValidTokenByUser(user.getId());
        if(tokens == null)
            return ;
        tokens.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
    }

    
    
}
