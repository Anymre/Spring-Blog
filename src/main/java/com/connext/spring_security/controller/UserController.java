package com.connext.spring_security.controller;

import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 13:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService UserService;

    @GetMapping("/{id}")
    public String getUser() {
        return "get";
    }

    @PostMapping("/{id}")
    public String addUser() {
        return "add";
    }

    @PutMapping("/{id}")
    public String changeUser() {
        return "change";
    }

    @DeleteMapping("/{id}")
    public String deleteUser() {
        return "delete";
    }

}
