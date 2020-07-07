package com.yingjianren.yingjianren.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Long> {
    // 查询排序，l1是时长满足要求，l2是类型满足要求，l3是年代满足要求，l4是语言满足要求
    // 可以在Query注解中假如一个属性countQuery,后面跟的字符串为最大查询范围，如下面的不写Query，默认为Query=""select count(*) from movie ..."
    @Query(value = "select * from movie where movie_id in (?1) and movie_id in (?2) and movie_id in (?3) and movie_id in (?4)", nativeQuery = true)
    Page<Movie> findMovieByOrder(List<Long> l1, List<Long> l2, List<Long> l3, List<Long> l4, Pageable pageable);

    // l1 查询满足时长要求的movie_id
    @Query(value = "select movie_id from movie where minute between :minute1 and :minute2", nativeQuery = true)
    List<Long> findIdByMinute(int minute1, int minute2);

    // l2 查询满足类型要求的movie_id
    @Query(value = "select movie_id from movie where genres like concat('%',?1,'%')", nativeQuery = true)
    List<Long> findIdByGenres(String genres);

    // l3 查询满足年代要求的movie_id
    @Query(value = "select movie_id from movie where year between :year1 and :year2", nativeQuery = true)
    List<Long> findIdByYear(int year1, int year2);

    // l4 查询满足语言要求的movie_id
    @Query(value = "select movie_id from movie where language like concat('%',?1,'%')", nativeQuery = true)
    List<Long> findIdByLanguage(String language);

    @Query(value = "select * from movie where actors like concat('%',?1,'%1')"+
    "or directors like concat('%',?1,'%1')"+
    "or moive_name like concat('%',?1,'%1')"+
    "or genres like concat('%',?1,'%1')"+
    "or language like concat('%',?1,'%1')"+
    "or region like concat('%',?1,'%1')"+
    "or tag like concat('%',?1,'%1')",
    nativeQuery = true)
    List<Movie> findMovieByKeywords(String keywords);

    @Query(value = "select * from movie where  genres like concat('%',?1,'%')",
            nativeQuery = true)
    List<Movie> findMovieByType(String type);

    // 依据movie_id寻找电影
    @Query(value="select * from movie where movie_id=?1", nativeQuery = true)
    Movie findMovieById(Long id);

    // 依据movie_id查找电影是否存在
    @Query(value="select count(*) from movie where movie_id=?1", nativeQuery = true)
    Long findIfExistByMovieId(Long movieId);
}