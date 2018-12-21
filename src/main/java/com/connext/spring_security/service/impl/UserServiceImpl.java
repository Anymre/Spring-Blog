package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.AuthorityRepository;
import com.connext.spring_security.dao.RoleGroupRepository;
import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
@Service
@Transactional(rollbackOn = {Exception.class})
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    RoleGroupRepository roleGroupRepository;
//    @Override
//    @Transactional
//    public void test() {
//        Optional<Authority> authority = authorityRepository.findByName("all");
//        Optional<RoleGroup> roleGroup = roleGroupRepository.findByName("admin");
//        List<Authority> authorities = roleGroup.map(rol -> rol.getAuthorities()).orElse(new ArrayList<Authority>());
//        authorities.add(authority.orElse(new Authority()));
//        roleGroupRepository.save(roleGroup.orElse(new RoleGroup()));
//        System.out.println(roleGroupRepository.findAll());
//    }
}
