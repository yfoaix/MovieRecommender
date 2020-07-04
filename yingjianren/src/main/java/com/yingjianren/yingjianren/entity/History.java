package com.yingjianren.yingjianren.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class History {
    @Id
    @TableGenerator(name = "history_gen",
            table="history_generator",
            pkColumnName="gen_id",
            valueColumnName="gen_value",
            pkColumnValue="CUSTOMER_PK",
            allocationSize=1,
            initialValue=384771
    )
    @GeneratedValue(generator = "history_gen")
    private Long historyId;

    @OneToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movieId")
    private Movie movie;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name="userId")
    private User user;

    private Date createdAt;

    @PrePersist
    void createdAt(){
        this.createdAt=new Date();
    }
}
