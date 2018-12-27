package com.connext.spring_security.controller;

import com.connext.spring_security.service.AuthorityService;
import com.connext.spring_security.util.ReturnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/25 15:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthrityController {
    private final AuthorityService authorityService;

    @Autowired
    public AuthrityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("auths",authorityService.findAll());
        return "auths";
    }
    @PostMapping
    @ResponseBody
    public String addAuthrity(@RequestParam String auth) {
        boolean result= authorityService.addAuthority(auth);
        return ReturnState.returnState(result);
    }
    @DeleteMapping
    @ResponseBody
    public String deleteRole(@RequestParam Integer id) {
        boolean result= authorityService.deleteAuthority(id);
        return ReturnState.returnState(result);
    }
}
