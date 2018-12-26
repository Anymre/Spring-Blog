package com.connext.spring_security.controller;

import com.connext.spring_security.entity.Message;
import com.connext.spring_security.service.MessageService;
import com.connext.spring_security.service.UserService;
import com.connext.spring_security.util.ReturnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable Integer id, Model model) {
        model.addAttribute("message",messageService.findMessage(id));
        return "detail";
    }

    @PostMapping("/{id}")
    public String addMessage(@RequestParam Message message) {
        boolean result = messageService.addMessage(message);
        return ReturnState.returnState(result);
    }

    @PutMapping("/{id}")
    public String changeMessage(@RequestParam Message message) {
        boolean result = messageService.changeMessage(message);
        return ReturnState.returnState(result);
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Integer id) {
        boolean result = messageService.deleteMessage(id);
        return ReturnState.returnState(result);
    }

    @GetMapping("/{id}/my")
    public List<Message> myMessage(@PathVariable Integer id) {
        return messageService.findMyMessage(id);
    }

    @PostMapping("/{id}/comment")
    public String comment(@PathVariable Integer id,@RequestParam String comment){
        messageService.comment(id,comment);
        return "redirect:/message/"+id;
    }
}
