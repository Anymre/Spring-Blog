package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.MessageRepository;
import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
@Service
@Transactional(rollbackOn = {Exception.class})
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Message> findALl() {
        Iterable<Message> messages = messageRepository.findAll();
        List<Message> list = new ArrayList<>();
        messages.forEach(list::add);
        return list;
    }

    @Override
    public void addMessage(Message message,Integer userId) {
        User user = userRepository.findById(userId).orElse(new User());
        message.setUser(user);
        user.getMessages().add(message);
        userRepository.save(user);
    }
}
