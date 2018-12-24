package com.connext.spring_security.controller;

import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.UserService;
import com.connext.spring_security.util.ReturnState;
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
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public List<User> allUser() {
        return userService.allUser();
    }

    @PostMapping("/{id}")
    public String addUser(User user) {
        boolean result = userService.register(user);
        return ReturnState.returnState(result);
    }
    @PostMapping("/{id}/role")
    public String setRole(@PathVariable Integer id, @RequestParam List<String> roles){
        boolean result=userService.setRole(id,roles);
        return ReturnState.returnState(result);
    }
}
