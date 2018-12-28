package com.connext.spring_security.service.impl;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {
    private String authority;
    public UserAuthority(String authority) {
        this.authority=authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
