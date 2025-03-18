package com.ochabdo.security.business.services;

import com.ochabdo.security.dao.entities.User;

public interface UserService {
    
    User getUserByEmail(String email);
}
