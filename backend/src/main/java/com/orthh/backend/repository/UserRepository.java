package com.orthh.backend.repository;

import org.apache.ibatis.annotations.Mapper;

import com.orthh.backend.domain.User;

@Mapper
public interface UserRepository {

    // 고유id를 이용해 User 엔터티 반환
    public User findById(Long id);

    // email 이용해 User 엔터티 반환
    public User findByEmail(String email);

    // 회원가입
    public void save(User user);
}