package com.ochabdo.security.dao.entities;

import com.ochabdo.security.web.dto.TypeToken;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String tokenname ;
    @Enumerated(EnumType.STRING)
    private TypeToken typetoken ;
    private boolean expired ;
    private boolean revoked ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

}
