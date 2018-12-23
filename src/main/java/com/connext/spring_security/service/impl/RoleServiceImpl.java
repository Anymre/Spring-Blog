package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.RoleGroupRepository;
import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 17:05
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleGroupRepository roleGroupRepository;

    @Override
    public List<RoleGroup> findAll() {
        Iterable<RoleGroup> roleGroups = roleGroupRepository.findAll();
        List<RoleGroup> list = new ArrayList<>();
        roleGroups.forEach(list::add);
        return list;
    }

    @Override
    public boolean addRole(String role) {
        roleGroupRepository.save(new RoleGroup(role));
        return true;
    }

    @Override
    public boolean deleteRole(String role) {
        roleGroupRepository.delete(roleGroupRepository.findByName(role).get());
        return true;
    }
}
