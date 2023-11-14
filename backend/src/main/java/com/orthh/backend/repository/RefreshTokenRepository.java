package com.orthh.backend.repository;

import org.apache.el.stream.Optional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orthh.backend.domain.RefreshToken;

import lombok.RequiredArgsConstructor;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}