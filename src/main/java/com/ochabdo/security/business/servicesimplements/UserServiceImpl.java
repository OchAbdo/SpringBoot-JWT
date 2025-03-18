package com.ochabdo.security.business.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ochabdo.security.business.services.UserService;
import com.ochabdo.security.dao.Repositories.UserRepository;
import com.ochabdo.security.dao.entities.User;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not Found")) ;
    }
    
}
