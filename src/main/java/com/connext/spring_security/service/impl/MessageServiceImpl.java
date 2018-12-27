package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.CommentRepository;
import com.connext.spring_security.dao.MessageRepository;
import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.Comment;
import com.connext.spring_security.entity.Message;
import com.connext.spring_security.entity.User;
import com.connext.spring_security.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
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
    private final CommentRepository commentRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Message> findAll(String page) {
        Iterable<Message> messages;
        PageRequest pageRequest = PageRequest.of(0, 5);
        switch (Integer.parseInt(page)) {
            case 0:
                messages = messageRepository.findAll(pageRequest.previous());
                break;
            case 1:
                messages = messageRepository.findAll(pageRequest.next());
                break;
            default:
                messages = messageRepository.findAll(pageRequest.first());
                break;
        }
        List<Message> list = new ArrayList<>();
        messages.forEach(list::add);
        return sort(list);
    }

    @Override
    public List<Message> findMyMessage() {
        Iterable<Message> messages = messageRepository.findAllByUserId(getUser().getId());
        List<Message> list = new ArrayList<>();
        messages.forEach(list::add);
        return sort(list);
    }

    @Override
    public Message findMessage(Integer messageId) {
        return messageRepository.findById(messageId).orElse(new Message());
    }

    @Override
    public boolean addMessage(String title, String context) {
        Message message = new Message(title, context, getUser());
        messageRepository.save(message);
        return true;
    }

    @Override
    public boolean changeMessage(Integer id, String title, String context) {
        String authority = "message_change";
        Optional<Message> oldMessage = messageRepository.findById(id);
        boolean canOrNot = oldMessage.isPresent() && (getUser().getId().equals(oldMessage.get().getUser().getId()) || userisHasAuthority(authority));
        if (canOrNot) {
            oldMessage.get().setTitle(title);
            oldMessage.get().setContext(context);
            messageRepository.save(oldMessage.get());
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
        return false;
    }

    @Override
    public void comment(Integer messageId, String context) {
        Optional<Message> oldMessage = messageRepository.findById(messageId);
        if (oldMessage.isPresent()) {
            Comment comment = new Comment(context, getUser(), oldMessage.get());
            commentRepository.save(comment);
            oldMessage.get().setCommentTime(comment.getCreateTime());
            messageRepository.save(oldMessage.get());
        }
    }


    public boolean userisHasAuthority(String authority) {
        GrantedAuthority grantedAuthority = new UserAuthority(authority);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        if (user.getAuthorities().contains(grantedAuthority)) {
            return true;
        }
        return false;
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        return userRepository.findByPhone(user.getUsername()).get();
    }

    public List<Message> sort(List<Message> list) {
        Collections.sort(list, ((o1, o2) -> {
            if (o1.getCommentTime().after(o2.getCommentTime())) {
                return -1;
            } else {
                return 1;
            }
        }));
        return list;
    }
}

