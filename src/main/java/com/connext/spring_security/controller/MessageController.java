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
        model.addAttribute("message", messageService.findMessage(id));
        return "detail";
    }

    @PostMapping("/add")
    public String addMessage(@RequestParam String title,@RequestParam String context) {
        boolean result = messageService.addMessage(title,context);
        return "redirect:/message/my";
    }
    @GetMapping("/{id}/change")
    public String getChangeMessage(@PathVariable Integer id,Model model){
        model.addAttribute("message",messageService.findMessage(id));
        return "message_add_edit";
    }
    @PostMapping("/{id}/change")
    public String changeMessage(@PathVariable Integer id,@RequestParam String title,@RequestParam String context) {
        boolean result = messageService.changeMessage(id,title,context);
        if(result){
            return "redirect:/message/my";
        }
        return "redirect:/error";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteMessage(@PathVariable Integer id) {
        boolean result = messageService.deleteMessage(id);
        return ReturnState.returnState(result);
    }

    @GetMapping("/my")
    public String myMessage(Model model) {
        model.addAttribute("messages", messageService.findMyMessage());
        return "my_message";
    }

    @PostMapping("/{id}/comment")
    public String comment(@PathVariable Integer id, @RequestParam String comment) {
        messageService.comment(id, comment);
        return "redirect:/message/" + id;
    }
}
