package com.yingjianren.yingjianren.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long commentId;

    @OneToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movieId")
    private Movie movie;

    /*
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user;
    */

    private String userMD5;

    private Date createdAt;

    private float rating;

    @Column(length = 7000)
    private String content;


    @PrePersist
    void createdAt(){
        this.createdAt=new Date();
    }

}
