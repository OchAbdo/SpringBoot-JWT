package com.ochabdo.security.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    STUDIANT_READ("Studiant:read"),
    STUDIANT_UPDATE("Studiant:update"),
    STUDIANT_CREATE("Studiant:create"),
    STUDIANT_DELETE("Studiant:delete")
    ;

    @Getter
    private final String permission;
}
