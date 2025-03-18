package com.ochabdo.security.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ochabdo.security.business.services.UserService;



@Configuration
public class ApplicationConfig {

    @Autowired
    private UserService userService ;

    /*@Autowired
    private UserRepository repository;*/


    @Bean
    public UserDetailsService userDetailsService()
    {
        return username-> this.userService.getUserByEmail(username);
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        return username -> this.repository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }*/
    
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider ;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder() ;
    }
}
