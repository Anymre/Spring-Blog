package com.connext.spring_security.controller;

import com.connext.spring_security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 16:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public boolean addRole(String role) {
        return roleService.addRole(role);
    }
    @DeleteMapping
    public boolean deleteRole(String role) {
        return roleService.deleteRole(role);
    }
}
