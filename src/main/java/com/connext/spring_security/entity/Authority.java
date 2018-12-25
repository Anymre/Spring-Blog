package com.connext.spring_security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
/**@Author: Marcus
 * @Date: 2018/12/20 10:21
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true,nullable=false)
    private String name;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifiedTime;

    public Authority(String name) {
        this.name = name;
    }
}
