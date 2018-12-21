package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByPhone(username);
        if (user.isPresent()) {
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            org.springframework.security.core.userdetails.User auth_user = new org.springframework.security.core.userdetails.User(user.get().getPhone(), user.get().getPassword(), list);
            return auth_user;
        } else {
            throw new UsernameNotFoundException("user not exist！");
        }
    }
}
