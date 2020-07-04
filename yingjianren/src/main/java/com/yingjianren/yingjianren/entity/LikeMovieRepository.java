package com.yingjianren.yingjianren.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeMovieRepository extends CrudRepository<LikeMovie,Long> {
    @Query(value="select * from like_movie c where c.user_id = ?1 and c.movie_id = ?2",nativeQuery = true)
    List<LikeMovie> findByUserAndMovie(Long user, Long movie);
    @Query(value="select * from like_movie c where c.user_id = ?1",nativeQuery = true)
    List<LikeMovie> findByUser(Long user);
    @Query(value="select * from like_movie c where c.movie_id = ?1",nativeQuery = true)
    List<LikeMovie> findByMovie(Long movie);
}
