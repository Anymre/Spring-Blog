package com.connext.spring_security.controller;

import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.RoleService;
import com.connext.spring_security.service.UserService;
import com.connext.spring_security.util.ReturnState;
import com.connext.spring_security.util.UseBCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 13:00
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public String allUser(Model model) {
        model.addAttribute("users",userService.allUser());
        return "users";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/{id}")
    @ResponseBody
    public String addUser(User user) {
        user.setPassword(UseBCrypt.Encoder(user.getPassword()));
        boolean result = userService.register(user);
        return ReturnState.returnState(result);
    }
    @GetMapping("/{id}/role")
    public String getUserRole(@PathVariable Integer id, Model model) {
        model.addAttribute("user",userService.getUser(id));
        model.addAttribute("roles",roleService.findAll());
        return "user_role";
    }
    @PostMapping("/{id}/role")
    @ResponseBody
    public String setRole(@PathVariable Integer id, @RequestParam String role) {
        List<String> roles= Arrays.asList(role.split(","));
        boolean result = userService.setRole(id, roles);
        return ReturnState.returnState(result);
    }
}
