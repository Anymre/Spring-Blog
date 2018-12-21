package com.connext.spring_security.dao;


import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:36
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessageRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;

    @Test
    public void addMessage() {

    }

    @Test
    public void MessageUser() {
        Optional<Message> message = messageRepository.findById(2);
        User user = message.map(m -> m.getUser()).orElse(new User());
        System.out.println(user);
    }
}