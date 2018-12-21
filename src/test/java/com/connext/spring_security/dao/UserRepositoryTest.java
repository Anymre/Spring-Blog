package com.connext.spring_security.dao;


import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:36
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CommentRepository commentRepository;
    User auser = new User("1843862705", "Marcus", "Marcus@qq.com");
    @Test
    public void insert() {
        userRepository.save(auser);
    }
    @Test
    public void addMessage(){
    }
}