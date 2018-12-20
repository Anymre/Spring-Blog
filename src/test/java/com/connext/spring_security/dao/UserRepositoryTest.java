package com.connext.spring_security.dao;


import com.connext.spring_security.entity.Comment;
import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

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
    public void ainsert() {
        userRepository.save(auser);
        System.out.println(userRepository.findAll());
    }

    @Test
    public void insertAndViewMyMessge() {
        User user = userRepository.findByPhone(auser.getPhone());
        Message amessage = new Message("1","1",user);
        user.getMessages().add(amessage);
        userRepository.save(user);
    }

    @Test
    public void comment() {
        Message message = messageRepository.findById(2).orElse(new Message());
        User user = userRepository.findByPhone(auser.getPhone());
        Comment comment = new Comment("this is comment", message, user);
        message.getComments().add(comment);
        messageRepository.save(message);
    }
}