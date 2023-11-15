package com.orthh.backend.repository;

import com.orthh.backend.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * refresh token redis에 저장하기 위한 repository
 */
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {}
