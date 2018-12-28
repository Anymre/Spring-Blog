package com.connext.spring_security.controller;

import com.connext.spring_security.entity.Message;
import com.connext.spring_security.service.MessageService;
import com.connext.spring_security.util.GetUser;
import com.connext.spring_security.util.ReturnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    private final GetUser getUser;

    @Autowired
    public MessageController(MessageService messageService, GetUser getUser) {
        this.messageService = messageService;
        this.getUser = getUser;
    }

    @GetMapping("/index/{page}")
    public String index(Model model, @PathVariable String page) {
        model.addAttribute("messages", messageService.findAll(page));
        return "index";
    }

    @GetMapping("/index/admin/{page}")
    public String indexAdmin(Model model, @PathVariable String page) {
        model.addAttribute("messages", messageService.findAll(page));
        return "index_admin";
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable Integer id, Model model) {
        model.addAttribute("message", messageService.findMessage(id));
        return "detail";
    }

    @PostMapping("/add")
    public String addMessage(@RequestParam String title, @RequestParam String context) {
        messageService.addMessage(title, context.substring(0, 200));
        return "redirect:/message/my";
    }

    @GetMapping("/{id}/change")
    public String getChangeMessage(@PathVariable Integer id, Model model) {
        model.addAttribute("message", messageService.findMessage(id));
        return "message_add_edit";
    }

    @PostMapping("/{id}/change")
    public String changeMessage(@PathVariable Integer id, @RequestParam String title, @RequestParam String context) {
        Message message = messageService.findMessage(id);
        String authority = "message_change";
        if ((getUser.getUserId().equals(message.getUser().getId()) || getUser.userHasAuthority(authority))) {
            messageService.changeMessage(id, title, context);
            return "redirect:/message/my";
        }
        return "redirect:/error";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteMessage(@PathVariable Integer id) {
        Message message = messageService.findMessage(id);
        String authority = "message_delete";
        if ((getUser.getUserId().equals(message.getUser().getId()) || getUser.userHasAuthority(authority))) {
            messageService.deleteMessage(id);
            return ReturnState.returnState(true);
        }
        return ReturnState.returnState(false);
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
