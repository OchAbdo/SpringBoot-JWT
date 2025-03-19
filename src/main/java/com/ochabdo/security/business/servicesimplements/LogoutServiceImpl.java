package com.ochabdo.security.business.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.ochabdo.security.dao.Repositories.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LogoutServiceImpl  implements LogoutHandler {

    @Autowired
    private TokenRepository tokenRepository ;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        
        final String authHeader = request.getHeader("Authorization");
        final String jwt ;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ;
        }
        jwt = authHeader.substring(7);
        var storedToken = this.tokenRepository.findByTokenname(jwt)
                                                .orElse(null);
        if(storedToken != null)
        {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            this.tokenRepository.save(storedToken);
        }
    }
    
}
