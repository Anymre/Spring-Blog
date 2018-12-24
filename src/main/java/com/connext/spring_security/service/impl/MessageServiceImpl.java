package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.AuthorityRepository;
import com.connext.spring_security.dao.MessageRepository;
import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.Authority;
import com.connext.spring_security.entity.Comment;
import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Marcus
 * @Date: 2018/12/21 10:18
 * @Version 1.0
 */
@Service
@Transactional(rollbackOn = {Exception.class})
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Message> findALl() {
        Iterable<Message> messages = messageRepository.findAll();
        List<Message> list = new ArrayList<>();
        messages.forEach(list::add);
        return list;
    }

    @Override
    public List<Message> findMyMessage(Integer id) {
        Iterable<Message> messages = messageRepository.findAllByUserId(id);
        List<Message> list = new ArrayList<>();
        messages.forEach(list::add);
        return list;
    }

    @Override
    public Message findMessage(Integer messageId) {
        return messageRepository.findById(messageId).orElse(new Message());
    }

    @Override
    public boolean addMessage(Message message) {
        message.setUser(getUser());
        getUser().getMessages().add(message);
        userRepository.save(getUser());
        return true;
    }

    @Override
    public boolean changeMessage(Message message) {
        String authority = "message_update";
        Optional<Message> oldMessage = messageRepository.findById(message.getId());
        if (oldMessage.isPresent() && (getUser().getId().equals(oldMessage.get().getUser().getId()) || userisHasAuthority(authority))) {
            oldMessage.get().setTitle(message.getTitle());
            oldMessage.get().setContext(message.getContext());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMessage(Integer messageId) {
        String authority = "message_delete";
        Optional<Message> oldMessage = messageRepository.findById(messageId);
        if (oldMessage.isPresent() && (getUser().getId().equals(oldMessage.get().getUser().getId()) || userisHasAuthority(authority))) {
            messageRepository.deleteById(messageId);
            return true;
        }
        return true;
    }

    @Override
    public void comment(Integer messageId, String context) {
        Optional<Message> oldMessage = messageRepository.findById(messageId);
        if (oldMessage.isPresent()){
            oldMessage.get().getComments().add(new Comment(context));
            oldMessage.get().setCommentTime(new Date());
            messageRepository.save(oldMessage.get());
        }
    }


    public boolean userisHasAuthority(String authority) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getDetails();
        Optional<Authority> theAuthority = authorityRepository.findByName(authority);
        if (theAuthority.isPresent()) {
            userDetails.getAuthorities().contains(theAuthority.get());
            return true;
        }
        return false;
    }

    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getDetails();
        return userRepository.findByPhone(userDetails.getUsername()).get();
    }
}

