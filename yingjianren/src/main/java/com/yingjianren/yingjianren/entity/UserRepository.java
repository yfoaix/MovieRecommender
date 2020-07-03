package com.yingjianren.yingjianren.entity;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    @Query(value = "select id from user where email=:email", nativeQuery = true)
    Long selectIdByEmail(@Param("email") String email);
}