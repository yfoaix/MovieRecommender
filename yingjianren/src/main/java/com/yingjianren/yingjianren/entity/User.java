package com.yingjianren.yingjianren.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @TableGenerator(name = "user_gen",
            table="user_generator",
            pkColumnName="gen_id",
            valueColumnName="gen_value",
            pkColumnValue="CUSTOMER_PK",
            allocationSize=1,
            initialValue=227207
    )

    @GeneratedValue(generator = "user_gen")
    private Long userId;//id,自增主键

    private String userName;//用户名

    private String email;//邮箱

    private String userPwd;//用户密码

    @Column(nullable = false)
    private boolean authentication;//是否是影荐人

    private String biography;//个人简介

    private String imgUrl;//头像

    @Column(nullable = false)
    private boolean confirm;//邮箱是否验证

}
