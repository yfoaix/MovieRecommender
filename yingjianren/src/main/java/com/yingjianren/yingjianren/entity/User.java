package com.yingjianren.yingjianren.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long userId;//id,自增主键

    private String userMD5;//MD5,id，数据库查询标识

    @Column(nullable = false)
    private String userName;//用户名

    //登录注册用
    // @Column(nullable = false)
    // private String userCount;//用户账号

    @Column(nullable = false)
    private String userPwd;//用户密码

    private boolean authentication;//是否是影荐人

    private String biography;//个人简介

    private String imgUrl;//头像

    @Column(nullable = false)
    private String email;//邮箱

    private boolean confirm;//邮箱是否验证

}
