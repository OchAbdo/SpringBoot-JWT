package com.ochabdo.security.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    
    private String firstname ;
    private String lastname ;
    private String email ;
    private String password ;
}
