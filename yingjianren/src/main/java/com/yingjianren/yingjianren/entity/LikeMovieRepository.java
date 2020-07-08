package com.yingjianren.yingjianren.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import javax.transaction.Transactional;

public interface LikeMovieRepository extends CrudRepository<LikeMovie,Long> {
    @Query(value="select * from like_movie c where c.user_id = ?1 and c.movie_id = ?2",nativeQuery = true)
    List<LikeMovie> findByUserAndMovie(Long user, Long movie);
    @Query(value="select * from like_movie c where c.user_id = ?1",nativeQuery = true)
    List<LikeMovie> findByUser(Long user);
    @Query(value="select * from like_movie c where c.movie_id = ?1",nativeQuery = true)
    List<LikeMovie> findByMovie(Long movie);

    // 依据history_id删除评论
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="delete from like_movie where movie_id=?1 and user_id=?2",nativeQuery = true)
    void deleteLikeByUserIdAndMovieId(Long movieId, Long userId);

    // 查找用户是否喜欢该电影
    @Query(value="select count(*) from like_movie where user_id=?1 and movie_id=?2 ",nativeQuery = true)
    int findIfExistLike(Long userId, Long movieId);

    // 分页查找用户喜欢的电影
    @Query(value="select * from like_movie where user_id=?1",nativeQuery = true)
    List<LikeMovie> findLikeByUserIdPage(Long userId, Pageable pageable);
}
