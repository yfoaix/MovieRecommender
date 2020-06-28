package com.yingjianren.yingjianren.entity;

import lombok.Data;

/**
 * User
 */
@Data
public class User {
    
    private int uId;
    private String uName;
    private String uPsd;
    private String uEmail;
    private boolean uIsLogin;
    private boolean uIsV;
    
}