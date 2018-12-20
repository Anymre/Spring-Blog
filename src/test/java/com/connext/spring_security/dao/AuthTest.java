package com.connext.spring_security.dao;


import com.connext.spring_security.entity.*;
import com.connext.spring_security.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:36
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    RoleGroupRepository roleGroupRepository;
    @Autowired
    UserService userService;
    User auser = new User("1843862705", "Marcus", "Marcus@qq.com");

    @Test
    public void insert() {
        authorityRepository.save(new Authority("all"));
        roleGroupRepository.save(new RoleGroup("admin"));
    }

    @Test
    public void auth() {
        userService.test();
    }
}