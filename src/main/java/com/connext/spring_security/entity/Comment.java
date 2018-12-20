package com.connext.spring_security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 15:35
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String context;
    @ManyToOne
    private Message message;
    @ManyToOne
    private User user;

    public Comment(String context, Message message, User user) {
        this.context = context;
        this.message = message;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", message=" + message +
                ", user=" + user.getNickname() +
                '}';
    }
}
