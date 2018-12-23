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
     * register a user
     *
     * @param user Information of user.
     * @return States of this.
     */
    String register(User user);

    /**
     * Add roles for user
     *
     * @param userId  The user who want to add Roles.
     * @param roles Roles
     * @return true or false
     */
    boolean addRole(Integer userId, List<String> roles);

    /**Add authoritiest for user
     * @param userId The user who want to add authorities
     * @param authorities Authorities
     * @return true or false
     */
    boolean addAuthority(Integer userId,List<String> authorities);
}
