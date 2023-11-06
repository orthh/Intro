package com.orthh.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //기본적으로 csrf 비활성화 시키고, 특정 엔드포인트 (ex. "/member/join", "/auth/login")를 인가 예외시키는 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .anyRequest().permitAll()
                                // .requestMatchers("/member/join").permitAll()
                                // .requestMatchers("/auth/login").permitAll()
                                // .requestMatchers("/swagger-ui").permitAll()
                                // .requestMatchers("/swagger-ui/*").permitAll()
                                // .anyRequest().authenticated()
                );
        return http.build();
    }
}