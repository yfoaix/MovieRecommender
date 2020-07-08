package com.yingjianren.yingjianren.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class LikeMovie {
    @Id
    @GeneratedValue
    private Long likeId;

    @OneToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movieId")
    private Movie movie;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user;
}