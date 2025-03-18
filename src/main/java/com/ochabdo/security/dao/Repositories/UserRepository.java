package com.ochabdo.security.dao.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ochabdo.security.dao.entities.User;

import java.util.Optional;




@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByEmail(String email);
    
}
