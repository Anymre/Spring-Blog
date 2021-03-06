package com.connext.spring_security.dao;

import com.connext.spring_security.entity.RoleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:25
 * @Version 1.0
 */
public interface RoleGroupRepository extends CrudRepository<RoleGroup, Integer> {
    /**
     * find a role with name
     * @param name role's name
     * @return the role
     */
    Optional<RoleGroup> findByName(String name);
}

