package com.yingjianren.yingjianren.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Long> {
    @Query(value="select count(*) from score where user_id=?1 and movie_id=?2", nativeQuery = true)
    public int findIfExistScore(Long userId, Long movieId);

    @Query(value="select score from score where movie_id = ?1 and user_id=?2", nativeQuery = true)
    public int fingScoreByUserIdAndMovieId(Long movieId, Long userId);
}