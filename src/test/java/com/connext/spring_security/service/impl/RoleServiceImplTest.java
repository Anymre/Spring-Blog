package com.connext.spring_security.service.impl;

import com.connext.spring_security.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 17:15
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
class RoleServiceImplTest {
    @Autowired
    RoleService roleService;
    String role="test1";
    @Test
    void findAll() {
        System.out.println(roleService.findAll());
    }

    @Test
    void addRole() {
        assertTrue(roleService.addRole(role));
    }

    @Test
    void deleteRole() {
    }
}