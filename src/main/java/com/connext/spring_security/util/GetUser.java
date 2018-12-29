package com.connext.spring_security.util;

import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.service.impl.UserAuthority;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @Author: Marcus
 * @Date: 2018/12/28 13:31
 * @Version 1.0
 */
@Component
@Data
public class GetUser {
    @Autowired
    private UserRepository userRepository;

    public User user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (org.springframework.security.core.userdetails.User) auth.getPrincipal();
    }

    public Integer getUserId() {
        return userRepository.findByPhone(user().getUsername()).get().getId();
    }
    public com.connext.spring_security.entity.User getUser(){
        return userRepository.findByPhone(user().getUsername()).get();
    }

    public boolean userHasAuthority(String authority) {
        GrantedAuthority grantedAuthority = new UserAuthority(authority);
        if (user().getAuthorities().contains(grantedAuthority)) {
            return true;
        }
        return false;
    }
}
