package com.connext.spring_security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**@Author: Marcus
 * @Date: 2018/12/20 10:21
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true, length = 11,nullable=false)
    private String phone;
    private String password;
    private String nickname;
    private String email;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifiedTime;
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "nickname")
    private List<Message> messages;
    @ManyToMany
    private List<RoleGroup> roleGroups;

    public User(String phone, String password,String nickname, String email) {
        this.phone = phone;
        this.nickname = nickname;
        this.email = email;
        this.password=password;
    }
}
