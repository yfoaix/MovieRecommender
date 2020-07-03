package com.yingjianren.yingjianren.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Comment {
    @Id
    @TableGenerator(name = "comment_gen",
            table="comment_generator",
            pkColumnName="gen_id",
            valueColumnName="gen_value",
            pkColumnValue="CUSTOMER_PK",
            allocationSize=1,
            initialValue=437430
    )
    @GeneratedValue(generator = "comment_gen")
    private Long commentId;

    @OneToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movieId")
    private Movie movie;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user;

    private Date createdAt;

    private float rating;

    @Column(length = 7000)
    private String content;


    @PrePersist
    void createdAt(){
        this.createdAt=new Date();
    }

}
