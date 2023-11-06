package com.orthh.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.orthh.backend.domain.User;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users where email = #{email} ")
    public User findByEmail(String email);
}