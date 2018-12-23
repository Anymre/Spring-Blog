package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.AuthorityRepository;
import com.connext.spring_security.dao.RoleGroupRepository;
import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.Authority;
import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional(rollbackOn = {Exception.class})
public class UserServiceImpl implements UserService {
    private final
    UserRepository userRepository;
    private final
    AuthorityRepository authorityRepository;
    private final
    RoleGroupRepository roleGroupRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, RoleGroupRepository roleGroupRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.roleGroupRepository = roleGroupRepository;
    }

    @Override
    public List<User> allUser() {
        Iterable<User> users = userRepository.findAll();
        List<User> list = new ArrayList<>();
        users.forEach(list::add);
        return list;
    }

    @Override
    public boolean register(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("register fail");
            return false;
        }
    }

    @Override
    public boolean setRole(Integer userId, List<String> roles) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().getRoleGroups().clear();
            List<RoleGroup> roleGroups = new ArrayList<>();
            roles.forEach(role -> {
                Optional<RoleGroup> roleGroup = roleGroupRepository.findByName(role);
                roleGroup.ifPresent(roleGroups::add);
            });
            user.get().getRoleGroups().addAll(roleGroups);
            userRepository.save(user.get());
            return true;
        } else {
            return false;
        }
    }

}
