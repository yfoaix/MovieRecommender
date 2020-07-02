package com.yingjianren.yingjianren.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Email {
    @Id
    @GeneratedValue
    private Long emailId;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user;

    @Column(nullable = false)
    private String email;//邮箱

    private boolean confirm;//邮箱是否验证
}
