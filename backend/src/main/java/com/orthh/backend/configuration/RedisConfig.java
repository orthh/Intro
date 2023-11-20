package com.orthh.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * refresh token 저장을 위한 redisConfig accesstoken 구현후 현재 refresh token 적용중이 아닙니다. 추후 추가 예정
 *
 * @author 김혁
 * @since 2023.11.14
 * @version 1.0
 */
@Configuration
public class RedisConfig {

  @Value("${spring.data.redis.host}")
  private String redisHost;

  @Value("${spring.data.redis.port}")
  private int redisPort;

  // Redis 연결을 설정하는 Bean
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(redisHost, redisPort);
  }

  // RedisTemplate을 생성하고 설정하는 Bean
  @Bean
  public RedisTemplate<String, String> redisTemplate() {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

    // Redis에 저장될 Key와 Value에 대한 직렬화/역직렬화를 설정
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());

    redisTemplate.setConnectionFactory(redisConnectionFactory());
    return redisTemplate;
  }
}
