package com.connext.spring_security.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/24 13:34
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorityServiceTest {
    @Autowired
    AuthorityService authorityService;

    @Test
    public void addALl() {
        List<String> authorities = new ArrayList<>(Arrays.asList("message_add","message_delete"));
        authorities.forEach(authorityService::addAuthority);
    }
}