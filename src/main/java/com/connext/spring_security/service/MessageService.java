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
     * get user's messages
     * @return user's messages
     */
    List<Message> findMyMessage();

    /**
     * get user's messages
     * @param messageId the message's id
     * @return user's messages
     */
    Message findMessage(Integer messageId);

    /**
     * add a message
     * @param title the title
     *                   * @param context the context
     * @return true or false
     */
    boolean addMessage(String title,String context);

    /**
     * change a message
     * @param id the message id
     * @param title the title
     * @param context the context
     * @return true or false
     */
    boolean changeMessage(Integer id,String title,String context);

    /**
     * delete a message
     *
     * @param messageId a message
     * @return true or false
     */
    boolean deleteMessage(Integer messageId);

    /**
     * add a comment
     * @param messageId a message
     * @param context the comment
     */
    void comment(Integer messageId,String context);

}
