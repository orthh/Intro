package com.orthh.backend.configuration;

import com.orthh.backend.security.jwt.JwtAuthenticationFilter;
import com.orthh.backend.service.JwtUserService;
import com.orthh.backend.service.impl.JwtUserServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  // cors 허용 값
  @Value("${cors.allowed.origins}")
  private String allowedOrigins;

  // HTTP 요청이 들어올 때 JWT(Json Web Token)를 확인하여 사용자 인증을 수행.
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  // 사용자의 상세 정보를 불러오는 등의 역할을 수행.
  private final JwtUserService jwtUserService;

  // 허용되는 URL 목록패턴
  private final String[] allowedUrls = {"/", "/v3/**", "/swagger-ui/**", "/v1/auth/**"};

  // 사용자에 허용된 URL 목록패턴
  private final String[] allowedUserUrls = {"/user/**"};

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable) // CSRF 보호를 비활성화
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(allowedUrls)
                    .permitAll() // 허용된 URL은 모든 요청을 허용
                    .requestMatchers(allowedUserUrls)
                    .hasAuthority("ROLE_USER") // ROLE_USER 에 허용하는 URL
                    .anyRequest()
                    .authenticated() // 그 외의 요청은 인증이 필요
            )
        .formLogin(AbstractHttpConfigurer::disable) // form 로그인을 비활성화
        .sessionManagement(
            session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 상태없이 관리
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(
            jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // jwt 인증필터 추가
        .cors(
            cors -> cors.configurationSource(corsConfigurationSource()) // cors 설정 적용
            );
    return http.build();
  }

  // 인증 공급자 설정
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(
        jwtUserService.userDetailsService()); // 사용자의 상세 정보를 불러올 때 사용할 서비스를 설정
    authProvider.setPasswordEncoder(passwordEncoder()); // 패스워드 암호화 방식 설정
    return authProvider;
  }

  // 인증 매니저는 사용자 인증을 담당하며, 이를 위해 인증 공급자를 이용.
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  // cors 설정
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true); // 사이트 간 요청 시 쿠키와 같은 자격 증명 정보를 포함할지 설정
    config.setAllowedOrigins(List.of(allowedOrigins)); // 요청을 허용할 origin을 설정
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    config.setAllowedHeaders(List.of("*")); // 요청에서 허용할 헤더를 설정
    config.setExposedHeaders(List.of("*")); // 응답에서 노출할 헤더를 설정

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  /*
   * 패스워드 암호화
   * BCrypt 암호화 설정
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
