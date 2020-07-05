package com.yingjianren.yingjianren.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    // 依据id查找特定用户对特定电影的评价
    @Query(value="select * from comment c where c.user_id = ?1 and c.movie_id = ?2",nativeQuery = true)
    List<Comment> findCommentByUserIdAndMovieId(Long userId, Long movieId);

    // 依据id查找用户的所有评价
    @Query(value="select * from comment c where c.user_id = ?1",nativeQuery = true)
    List<Comment> findCommentByUserId(Long userId);

    // 依据id查找电影所有的评价
    @Query(value="select * from comment c where c.movie_id = ?1",nativeQuery = true)
    List<Comment> findCommentByMovieId(Long movieId);

    // 依据id查找特定用户对特定电影的评价,只查找commentid最小的
    @Query(value="select * from comment c where c.user_id = ?1 and c.movie_id = ?2 order by c.comment_id asc limit 1",nativeQuery = true)
    Comment findOneCommentByUserIdAndMovieId(Long userId, Long movieId);

    // 依据id查找此用户外其他用户的评价,分页查询
    @Query(value="select * from comment c where c.user_id <> ?1 and c.movie_id = ?2",nativeQuery = true)
    List<Comment> findOtherCommentByUserID(Long userId, Long movieId, Pageable pageable);
}