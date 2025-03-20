package com.ochabdo.security.web.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.ochabdo.security.web.dto.Permission.ADMIN_CREATE;
import static com.ochabdo.security.web.dto.Permission.ADMIN_DELETE;
import static com.ochabdo.security.web.dto.Permission.ADMIN_READ;
import static com.ochabdo.security.web.dto.Permission.ADMIN_UPDATE;
import static com.ochabdo.security.web.dto.Permission.STUDIANT_CREATE;
import static com.ochabdo.security.web.dto.Permission.STUDIANT_DELETE;
import static com.ochabdo.security.web.dto.Permission.STUDIANT_READ;
import static com.ochabdo.security.web.dto.Permission.STUDIANT_UPDATE;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
        Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  STUDIANT_READ,
                  STUDIANT_UPDATE,
                  STUDIANT_DELETE,
                  STUDIANT_CREATE
          )
    ),
    STUDIANT(
        Set.of(
            STUDIANT_READ,
            STUDIANT_UPDATE,
            STUDIANT_DELETE,
            STUDIANT_CREATE
        )

    ),
    TEACHER(Collections.emptySet()), 
    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
