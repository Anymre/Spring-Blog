package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.UserService;
import com.connext.spring_security.util.UseBCrypt;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 16:31
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    List<String> roles = new ArrayList<String>(Arrays.asList("normal"));
    List<String> auth = new ArrayList<String>(Arrays.asList("message_change", "message_delete"));
    User user = new User("18438627095","123456","Marcus", "18438627095@163.com");

    @Test
    void UserAuth() {
        List<RoleGroup> roleGroups= userRepository.findByPhone(user.getPhone()).get().getRoleGroups();
        roleGroups.stream().map(u->u.getAuthorities()).forEach(System.out::println);
    }

    @Test
    void register() {
        userRepository.save(user);
    }

    @Test
    void addRole() {
        assertTrue(userService.setRole(1, roles));
    }

}