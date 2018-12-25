package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.AuthorityRepository;
import com.connext.spring_security.dao.RoleGroupRepository;
import com.connext.spring_security.entity.Authority;
import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 17:05
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackOn = {Exception.class})
public class RoleServiceImpl implements RoleService {
    private final RoleGroupRepository roleGroupRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public RoleServiceImpl(RoleGroupRepository roleGroupRepository, AuthorityRepository authorityRepository) {
        this.roleGroupRepository = roleGroupRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<RoleGroup> findAll() {
        Iterable<RoleGroup> roleGroups = roleGroupRepository.findAll();
        List<RoleGroup> list = new ArrayList<>();
        roleGroups.forEach(list::add);
        return list;
    }

    @Override
    public RoleGroup findOne(Integer id) {
        return roleGroupRepository.findById(id).get();
    }

    @Override
    public boolean addRole(String role) {
        Optional<RoleGroup> roleGroup = roleGroupRepository.findByName(role);
        if (roleGroup.isPresent()) {
            return false;
        }
        roleGroupRepository.save(new RoleGroup(role));
        return true;
    }

    @Override
    public boolean deleteRole(Integer id) {
        try {
            roleGroupRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public boolean setAuthority(Integer roleId, List<String> authorities) {
        Optional<RoleGroup> roleGroup = roleGroupRepository.findById(roleId);
        if (roleGroup.isPresent()) {
            roleGroup.get().getAuthorities().clear();
            List<Authority> listAuthority = new ArrayList<>();
            authorities.stream().map(authorityRepository::findByName).forEach(authority -> authority.ifPresent(listAuthority::add));
            roleGroup.get().getAuthorities().addAll(listAuthority);
            roleGroupRepository.save(roleGroup.get());
            return true;
        } else {
            return false;
        }
    }
}
