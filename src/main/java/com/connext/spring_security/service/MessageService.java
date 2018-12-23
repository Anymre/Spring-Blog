package com.connext.spring_security.service;

import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:17
 * @Version 1.0
 */
public interface MessageService {
    /**
     * get all messages
     *
     * @return all messages
     */
    List<Message> findALl();

    /**
     * add a message
     *
     * @param message a message
     * @param userId  the owner of the message
     * @return true or false
     */
    void addMessage(Message message, Integer userId);

    /**
     * delete a message
     *
     * @param messageId a message
     */
    void deleteMessage(Integer messageId);

}
