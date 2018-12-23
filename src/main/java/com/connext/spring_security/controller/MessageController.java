package com.connext.spring_security.controller;

import com.connext.spring_security.entity.Message;
import com.connext.spring_security.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return messageService.findALl();
    }

    @GetMapping("/my")
    public List<Message> myMessage() {
        return null;
    }

    @GetMapping("/{id}")
    public String getMessage() {
        return "get";
    }

    @PostMapping("/{id}")
    public String addMessage() {
        return "add";
    }

    @PutMapping("/{id}")
    public String changeMessage() {
        return "change";
    }

    @DeleteMapping("/{id}")
    public String deleteMessage() {
        return "delete";
    }
}
