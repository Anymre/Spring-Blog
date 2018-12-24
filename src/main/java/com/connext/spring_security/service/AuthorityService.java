package com.connext.spring_security.service;

import com.connext.spring_security.entity.Authority;
import com.connext.spring_security.entity.RoleGroup;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 17:03
 * @Version 1.0
 */
public interface AuthorityService {
    /**
     * return All authorities
     * @return All authorities
     */
    List<Authority> findAll();



    /**
     * add authority
     * @param authority authority
     * @return state
     */
    boolean addAuthority(String authority);

    /**
     * delete authority
     * @param authority the authority
     * @return state
     */
    boolean deleteAuthority(String authority);
}
