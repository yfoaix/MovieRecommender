package com.yingjianren.yingjianren.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import javax.transaction.Transactional;

public interface HistoryRepository extends CrudRepository<History, Long> {
    @Query(value = "select * from history c where c.user_id = ?1 and c.movie_id = ?2", nativeQuery = true)
    List<History> findHistoryByUseIdAndMovieId(Long userId, Long movieId);

    @Query(value = "select * from history c where c.user_id = ?1", nativeQuery = true)
    List<History> findHistoryByUserId(Long userId);

    @Query(value = "select * from history c where c.movie_id = ?1", nativeQuery = true)
    List<History> findHistoryByMovieId(Long movieId);

    // 取时间最近的一条浏览记录
    @Query(value = "select * from history where user_id = ?1 order by created_at desc limit 1", nativeQuery = true)
    History findRecentHistoryByUserID(Long userId);

    // 分页查询用户的浏览记录
    @Query(value = "select * from history c where c.user_id = ?1", nativeQuery = true)
    List<History> findHistoryByUserId(Long userId, Pageable pageable);

    // 依据history_id删除评论
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="delete from history where history_id=?1",nativeQuery = true)
    int deleteHistoryById(Long historyId);
}
