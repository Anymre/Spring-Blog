package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.AuthorityRepository;
import com.connext.spring_security.entity.Authority;
import com.connext.spring_security.entity.RoleGroup;
import com.connext.spring_security.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/23 18:19
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackOn = {Exception.class})
public class AuthorityServiceImpl implements AuthorityService {
    private  final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> findAll() {
        Iterable<Authority> authorities = authorityRepository.findAll();
        List<Authority> list = new ArrayList<>();
        authorities.forEach(list::add);
        return list;
    }

    @Override
    public boolean addAuthority(String authority) {
        try {
            authorityRepository.save(new Authority(authority));
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public boolean deleteAuthority(Integer id) {
        try {
            authorityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }
}
