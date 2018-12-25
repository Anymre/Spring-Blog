package com.connext.spring_security.controller;

import com.connext.spring_security.service.AuthorityService;
import com.connext.spring_security.service.RoleService;
import com.connext.spring_security.util.ReturnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 16:59
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    private final AuthorityService authorityService;

    @Autowired
    public RoleController(RoleService roleService, AuthorityService authorityService) {
        this.roleService = roleService;
        this.authorityService = authorityService;
    }

    @GetMapping
    public String getRoles(Model model){
        model.addAttribute("roles",roleService.findAll());
        return "roles";
    }

    @PostMapping
    @ResponseBody
    public String addRole(@RequestParam String role) {
        boolean result= roleService.addRole(role);
        return ReturnState.returnState(result);
    }
    @DeleteMapping
    @ResponseBody
    public String deleteRole(@RequestParam Integer id) {
        boolean result= roleService.deleteRole(id);
        return ReturnState.returnState(result);
    }
    @GetMapping("/{id}/auth")
    public String authrity(@PathVariable Integer id,Model model){
        model.addAttribute("role",roleService.findOne(id));
        model.addAttribute("authorities",authorityService.findAll());
        return "role_auth";
    }
    @PostMapping("/{id}/auth")
    @ResponseBody
    public String setAuthrity(@PathVariable Integer id, @RequestParam String authority){
        List<String> authorities= Arrays.asList(authority.split(","));
        boolean result= roleService.setAuthority(id,authorities);
        return ReturnState.returnState(result);
    }
}
