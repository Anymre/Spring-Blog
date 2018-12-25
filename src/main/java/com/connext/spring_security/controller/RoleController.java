package com.connext.spring_security.controller;

import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.service.RoleService;
import com.connext.spring_security.util.ReturnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
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
    public String deleteRole(@RequestParam String  role) {
        boolean result= roleService.deleteRole(role);
        return ReturnState.returnState(result);
    }
    @PutMapping("/{id}/auth")
    @ResponseBody
    public String setAuthrity(Integer id, List<String> authorities){
        boolean result= roleService.setAuthority(id,authorities);
        return ReturnState.returnState(result);
    }
}
