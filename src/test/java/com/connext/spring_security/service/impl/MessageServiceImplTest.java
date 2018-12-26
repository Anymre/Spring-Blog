package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:30
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class MessageServiceImplTest {
    @Autowired
    MessageService messageService;
    @Autowired
    UserRepository userRepository;
    Message amessage = new Message("1", "1", new User());

    @Test
    void addMessage() {
        messageService.addMessage(amessage.getTitle(),amessage.getContext());
    }

    @Test
    void findAll() {
        List<Message> messageList = messageService.findAll("2");
        messageList.forEach(System.out::println);
        messageList = messageService.findAll("1");
        messageList.forEach(System.out::println);
        messageList = messageService.findAll("1");
        messageList.forEach(System.out::println);
        messageList = messageService.findAll("0");
        messageList.forEach(System.out::println);
        messageList = messageService.findAll("0");
        messageList.forEach(System.out::println);
    }
}