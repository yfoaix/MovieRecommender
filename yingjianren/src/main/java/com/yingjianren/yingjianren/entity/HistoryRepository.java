package com.yingjianren.yingjianren.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History,Long> {
    @Query(value="select * from history c where c.user_id = ?1 and c.movie_id = ?2",nativeQuery = true)
    List<History> findByUserAndMovie(Long user, Long movie);
    @Query(value="select * from history c where c.user_id = ?1",nativeQuery = true)
    List<History> findByUser(Long user);
    @Query(value="select * from history c where c.movie_id = ?1",nativeQuery = true)
    List<History> findByMovie(Long movie);
}
