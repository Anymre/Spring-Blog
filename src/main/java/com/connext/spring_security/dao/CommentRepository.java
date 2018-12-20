package com.connext.spring_security.dao;

import com.connext.spring_security.entity.Comment;
import com.connext.spring_security.entity.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:25
 * @Version 1.0
 */
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}

