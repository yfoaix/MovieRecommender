package com.yingjianren.yingjianren.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    @Query("select * from comment c where c.user_id = ?1 and c.movie_id = ?2")
    List<Comment> findByUserAndMovie(Long user, Long movie);
    @Query("select * from comment c where c.user_id = ?1")
    List<Comment> findByUser(Long user);
    @Query("select * from comment c where c.movie_id = ?1")
    List<Comment> findByMovie(Long movie);
}
