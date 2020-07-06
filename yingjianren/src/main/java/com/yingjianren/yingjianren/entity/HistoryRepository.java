package com.yingjianren.yingjianren.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History,Long> {
    @Query(value="select * from history c where c.user_id = ?1 and c.movie_id = ?2",nativeQuery = true)
    List<History> findHistoryByUseIdAndMovieId(Long userId, Long movieId);
    @Query(value="select * from history c where c.user_id = ?1",nativeQuery = true)
    List<History> findHistoryByUserId(Long userId);
    @Query(value="select * from history c where c.movie_id = ?1",nativeQuery = true)
    List<History> findHistoryByMovieId(Long movieId);
}
