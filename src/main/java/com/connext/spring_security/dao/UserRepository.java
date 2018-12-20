package com.connext.spring_security.dao;

import com.connext.spring_security.entity.Authority;
import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:25
 * @Version 1.0
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * fetch data by phone
     * @param phone user's phone
     * @return User
     */
    Optional<User> findByPhone(String phone);
}

