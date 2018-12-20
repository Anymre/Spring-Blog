package com.connext.spring_security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: Marcus @Date: 2018/12/20 10:21 @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true, length = 11)
    private String phone;
    private String nickname;
    private String email;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL},fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Message> messages;

    public User(String phone, String nickname, String email) {
        this.phone = phone;
        this.nickname = nickname;
        this.email = email;
    }
}
