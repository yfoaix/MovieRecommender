package com.yingjianren.yingjianren.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Score {
    @Id
    @TableGenerator(name = "score_gen",
            table="score_generator",
            pkColumnName="gen_id",
            valueColumnName="gen_value",
            pkColumnValue="CUSTOMER_PK",
            allocationSize=1,
            initialValue=1
    )
    @GeneratedValue(generator = "score_gen")
    private Long scoreId;

    private int score;

    @OneToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movieId")
    private Movie movie;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user;
}