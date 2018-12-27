package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.RoleGroupRepository;
import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.UserService;
import com.connext.spring_security.util.Redis;
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
    private final Redis redis;
    private final
    UserRepository userRepository;
    private final
    RoleGroupRepository roleGroupRepository;

    @Autowired
    public UserServiceImpl(Redis redis, UserRepository userRepository, RoleGroupRepository roleGroupRepository) {
        this.redis = redis;
        this.userRepository = userRepository;
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
    public Integer getUser(String phone) {
        return userRepository.findByPhone(phone).orElse(new User()).getId();
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public String register(User user, String code) {
        if (!redis.vaildCode(user.getPhone(), code)) {
            return "Code error";
        }
        Optional<User> olduser = userRepository.findByPhone(user.getPhone());
        if(olduser.isPresent()){
            return "false";
        }
        userRepository.save(user);
        return "true";
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
