package com.connext.spring_security.dao;

import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:25
 * @Version 1.0
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
}

