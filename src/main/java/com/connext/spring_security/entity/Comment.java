package com.connext.spring_security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 15:35
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String context;
    @ManyToOne
    private User user;
    @ManyToOne
    private Message message;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifiedTime;

    public Comment(String context, User user, Message message) {
        this.context = context;
        this.user = user;
        this.message = message;
    }
}
