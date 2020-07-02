package com.yingjianren.yingjianren.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
public class Movie {
    @Id
    private Long movieId;

    @Column(nullable = false)
    private String moiveName;

    @Column(nullable = false)
    private String actors;

    @Column(nullable = false)
    private String directors;

    private String imgUrl;

    private float score;

    private long vote;

    private String genres;

    private String language;

    private int minute;

    private String region;

    @Column(length = 7000)
    private String storyLine;

    private String tag;

    private int year;





}
