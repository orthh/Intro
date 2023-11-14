package com.orthh.backend.repository;

import org.apache.ibatis.annotations.Mapper;

import com.orthh.backend.domain.User;

@Mapper
public interface UserRepository {

    public User findByEmail(String email);

    public void save(User user);
}