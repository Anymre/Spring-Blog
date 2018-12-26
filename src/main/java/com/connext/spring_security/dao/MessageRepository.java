package com.connext.spring_security.dao;

import com.connext.spring_security.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:25
 * @Version 1.0
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
    /**
     * get user's messages
     * @param user_id the user
     * @return user's messages
     */
    List<Message> findAllByUserId(Integer user_id);

    /**
     * page of messages
     * @param page page of messages
     * @return messages
     */
    Page<Message> findAll(Pageable page);
}

