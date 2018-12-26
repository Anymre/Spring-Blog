package com.connext.spring_security.controller;

import com.connext.spring_security.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Marcus
 * @Date: 2018/12/26 9:29
 * @Version 1.0
 */
@Controller
public class IndexController {
    private final MessageService messageService;

    @Autowired
    public IndexController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("messages",messageService.findALl());
        return "index";
    }

}
