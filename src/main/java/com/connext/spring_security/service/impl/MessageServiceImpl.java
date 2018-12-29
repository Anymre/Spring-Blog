package com.connext.spring_security.service.impl;

import com.connext.spring_security.dao.CommentRepository;
import com.connext.spring_security.dao.MessageRepository;
import com.connext.spring_security.dao.UserRepository;
import com.connext.spring_security.entity.Comment;
import com.connext.spring_security.entity.Message;
import com.connext.spring_security.service.MessageService;
import com.connext.spring_security.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final GetUser getUser;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, CommentRepository commentRepository, GetUser getUser) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.getUser = getUser;
    }

    @Override
    public List<Message> findAll(String page) {
        Iterable<Message> messages;
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.Direction.DESC,"commentTime");
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
        Iterable<Message> messages = messageRepository.findAllByUserId(getUser.getUserId());
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
        Message message = new Message(title, context, getUser.getUser());
        messageRepository.save(message);
        return true;
    }

    @Override
    public boolean changeMessage(Integer id, String title, String context) {
        Optional<Message> oldMessage = messageRepository.findById(id);
            oldMessage.get().setTitle(title);
            oldMessage.get().setContext(context);
            messageRepository.save(oldMessage.get());
            return true;
    }

    @Override
    public boolean deleteMessage(Integer messageId) {
        Optional<Message> oldMessage = messageRepository.findById(messageId);
            messageRepository.deleteById(messageId);
            return true;
    }

    @Override
    public void comment(Integer messageId, String context) {
        Optional<Message> oldMessage = messageRepository.findById(messageId);
        if (oldMessage.isPresent()) {
            Comment comment = new Comment(context, getUser.getUser(), oldMessage.get());
            commentRepository.save(comment);
            oldMessage.get().setCommentTime(comment.getCreateTime());
            messageRepository.save(oldMessage.get());
        }
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

