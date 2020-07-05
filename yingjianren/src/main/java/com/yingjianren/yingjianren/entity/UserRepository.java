package com.yingjianren.yingjianren.entity;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    // 依据邮箱找id
    @Query(value = "select user_id from user where email=:email", nativeQuery = true)
    Long findIdByEmail(@Param("email") String email);

    // 判断邮箱是否存在
    @Query(value = "select count(*) from user where email=:email", nativeQuery = true)
    int findIsExistEmail(@Param("email") String email);

    // 依据邮箱找密码
    @Query(value = "select user_pwd from user where email=:email", nativeQuery = true)
    String findPwdByEmail(@Param("email") String email);

    // 依据id找用户名
    @Query(value="select user_name from user where user_id = ?1", nativeQuery = true)
    String findNameById(@Param("userId") Long id);

    // 依据id查找整个User对象
    @Query(value="select * from user where user_id = ?1", nativeQuery = true)
    User findUserById(@Param("userId") Long id);
}