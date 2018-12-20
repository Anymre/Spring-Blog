package com.connext.spring_security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2018/12/20 10:58
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String context;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifiedTime;
    private Date commentTime;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "message",cascade = {CascadeType.ALL},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Comment> comments;

    public Message(String title, String context,User user) {
        this.title = title;
        this.context = context;
        this.user=user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                ", commentTime=" + commentTime +
                ", user=" + user.getNickname() +
                '}';
    }
}
