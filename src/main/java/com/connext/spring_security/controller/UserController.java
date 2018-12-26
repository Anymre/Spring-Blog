package com.connext.spring_security.controller;

import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.RoleService;
import com.connext.spring_security.service.UserService;
import com.connext.spring_security.util.Redis;
import com.connext.spring_security.util.ReturnState;
import com.connext.spring_security.util.UseBCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 13:00
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final Redis redis;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(Redis redis, UserService userService, RoleService roleService) {
        this.redis = redis;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public String allUser(Model model) {
        model.addAttribute("users", userService.allUser());
        return "users";
    }

    @PostMapping("/reg")
    @ResponseBody
    public String addUser(@RequestParam String phone, @RequestParam String password, @RequestParam String nickname, @RequestParam String email, @RequestParam String Code) {
        User user = new User(phone, password, nickname, email);
        user.setPassword(UseBCrypt.Encoder(user.getPassword()));
        String result = userService.register(user, Code);
        return result;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/code/{phone}")
    @ResponseBody
    public void userRegCode(@PathVariable String phone) {
        redis.getCode(phone);
    }

    @GetMapping("/{id}/role")
    public String getUserRole(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("hasrole", userService.getUser(id).getRoleGroups().stream().map(u -> u.getName()).collect(Collectors.toList()));
        model.addAttribute("roles", roleService.findAll());
        return "user_role";
    }

    @PostMapping("/{id}/role")
    @ResponseBody
    public String setRole(@PathVariable Integer id, @RequestParam String role) {
        List<String> roles = Arrays.asList(role.split(","));
        boolean result = userService.setRole(id, roles);
        return ReturnState.returnState(result);
    }
}
