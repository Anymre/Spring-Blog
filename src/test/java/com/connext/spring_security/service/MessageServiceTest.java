package com.connext.spring_security.service;

import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/28 13:32
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MessageServiceTest {
    @Autowired
    MessageService messageService;
    Message message = new Message("2", "2", new User());

    @Test
    public void update() {
        messageService.changeMessage(46, message.getTitle(), message.getContext());
        Message change = messageService.findMessage(46);
        assertAll(
                () -> assertEquals(change.getTitle(), message.getTitle()),
                () -> assertEquals(message.getContext(), change.getTitle()));
    }

    @Test
    public void delete() {
        messageService.deleteMessage(46);
        Message old = messageService.findMessage(46);
        assertNull(old.getTitle());
    }
}