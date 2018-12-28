package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.Authority;
import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.entity.User;
import com.connext.spring_security.util.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
@Service
@Transactional(rollbackOn = {Exception.class})
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final Redis redis;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository, Redis redis) {
        this.userRepository = userRepository;
        this.redis = redis;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        Optional<User> user = userRepository.findByPhone(username);
        if (user.isPresent()) {
            if (!redis.validPhone(username)) {
                throw new BadCredentialsException("you must to wait 120s!");
            }
            List<RoleGroup> roleGroups = user.get().getRoleGroups();
            List<String> authorities = new ArrayList<>();
            for (RoleGroup i : roleGroups) {
                authorities.addAll(i.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList()));
            }
            List<GrantedAuthority> list = new ArrayList<>(authorities.stream().map(UserAuthority::new).collect(Collectors.toList()));
            return new org.springframework.security.core.userdetails.User(user.get().getPhone(), user.get().getPassword(), list);
        } else {
            throw new BadCredentialsException("no reg!");
        }
    }
}

