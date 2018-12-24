package com.connext.spring_security.service;

import com.connext.spring_security.entity.User;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
public interface UserService {
    /**
     * Find all user.
     * @return All user.
     */
    List<User> allUser();

    /**
     * Find a user.
     * @param phone user's phone
     * @return a user.
     */
    Integer getUser(String phone);

    /**
     * Find a user.
     * @param id user's id
     * @return a user.
     */
    User getUser(Integer id);
    /**
     * register a user
     *
     * @param user Information of user.
     * @return States of this.
     */
    boolean register(User user);

    /**
     * Add roles for user
     *
     * @param userId  The user who want to add Roles.
     * @param roles Roles
     * @return true or false
     */
    boolean setRole(Integer userId, List<String> roles);
}
