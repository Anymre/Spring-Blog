package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.AuthorityRepository;
import com.connext.spring_security.dao.RoleGroupRepository;
import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.Authority;
import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    RoleGroupRepository roleGroupRepository;
    @Override
    @Transactional
    public void test() {
        Optional<Authority> authority = authorityRepository.findByName("all");
        Optional<RoleGroup> roleGroup = roleGroupRepository.findByName("admin");
        List<Authority> authorities = roleGroup.map(rol -> rol.getAuthorities()).orElse(new ArrayList<Authority>());
        authorities.add(authority.orElse(new Authority()));
        roleGroupRepository.save(roleGroup.orElse(new RoleGroup()));
        System.out.println(roleGroupRepository.findAll());
    }
}
