package com.yingjianren.yingjianren.model;

import java.util.Date;

public class CommentArea {
    private String userName;
    private String comment;
    private Date createInfo;
    private String imgUrl;
    private Long userId;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CommentArea(String userName, String comment, Date createInfo, String imgUrl, Long userId) {
        this.userName = userName;
        this.comment = comment;
        this.createInfo = createInfo;
        this.imgUrl = imgUrl;
        this.userId = userId;
    }
}