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

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user;

    private Date createdAt;

    private float rating;

    private String content;


    @PrePersist
    void createdAt(){
        this.createdAt=new Date();
    }

}
