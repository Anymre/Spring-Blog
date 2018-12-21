package com.connext.spring_security.controller;

import com.connext.spring_security.entity.Message;
import com.connext.spring_security.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/all")
    public List<Message> allMessage() {
        messageService.findALl().stream().map(m->m.getUser().getNickname()).forEach(System.out::println);
        return messageService.findALl();
    }
}
