package com.connext.spring_security.service;

import com.connext.spring_security.entity.RoleGroup;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 17:03
 * @Version 1.0
 */
public interface RoleService {
    /**
     * return All roles
     * @return All roles
     */
    List<RoleGroup> findAll();

    /**
     * add role
     * @param role the role
     * @return state
     */
    boolean addRole(String role);

    /**
     * delete role
     * @param role the role
     * @return state
     */
    boolean deleteRole(String role);

    /**Add authoritiest for role
     * @param roleId The role who want to add authorities
     * @param authorities Authorities
     * @return true or false
     */
    boolean setAuthority(Integer roleId,List<String> authorities);
}
