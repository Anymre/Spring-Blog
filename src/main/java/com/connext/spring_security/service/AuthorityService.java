package com.connext.spring_security.service;

import com.connext.spring_security.entity.Authority;

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
     * @param id the id of authority
     * @return state
     */
    boolean deleteAuthority(Integer id);
}
