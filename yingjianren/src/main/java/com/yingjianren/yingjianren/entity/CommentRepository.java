package com.yingjianren.yingjianren.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    @Query(value="select * from comment c where c.user_id = ?1 and c.movie_id = ?2",nativeQuery = true)
    List<Comment> findByUserAndMovie(Long user, Long movie);
    @Query(value="select * from comment c where c.user_id = ?1",nativeQuery = true)
    List<Comment> findByUser(Long user);
    @Query(value="select * from comment c where c.movie_id = ?1",nativeQuery = true)
    List<Comment> findByMovie(Long movie);
}
