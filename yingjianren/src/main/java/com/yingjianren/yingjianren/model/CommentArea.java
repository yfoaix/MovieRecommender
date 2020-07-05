package com.yingjianren.yingjianren.model;

import java.util.Date;

public class CommentArea {
    private String userName;
    private String comment;
    private Date createInfo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(Date createInfo) {
        this.createInfo = createInfo;
    }

    public CommentArea(String userName, String comment, Date createInfo) {
        this.userName = userName;
        this.comment = comment;
        this.createInfo = createInfo;
    }
}