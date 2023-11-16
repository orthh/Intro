package com.orthh.backend.security.jwt;

import com.orthh.backend.service.JwtService;
import com.orthh.backend.service.JwtUserService;
import com.orthh.backend.service.impl.JwtServiceImpl;
import com.orthh.backend.service.impl.JwtUserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/*
 * HTTP 요청이 올 때마다 한 번씩 실행되는 필터를 정의한 클래스
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final JwtUserService userService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader =
        request.getHeader("Authorization"); // 요청 헤더에서 "Authorization" 값을 가져옵니다.
    final String jwt;
    final String userEmail;

    // Authorization 헤더가 비어있거나 "Bearer "로 시작하지 않는 경우, 필터의 동작을 종료하고 다음 필터로 넘어갑니다.
    if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    // "Bearer " 다음부터가 실제 JWT이므로, 이를 추출합니다.
    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUserName(jwt); // JWT에서 사용자 이메일을 추출합니다.

    // 사용자 이메일이 존재하고, 현재 Security Context에 인증 정보가 없는 경우
    if (StringUtils.isNotEmpty(userEmail)
        && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails =
          userService.userDetailsService().loadUserByUsername(userEmail); // 사용자의 상세 정보를 불러옵니다.
      // JWT가 유효한 경우, Security Context에 인증 정보를 설정합니다.
      if (jwtService.isTokenValid(jwt, userDetails)) {
        SecurityContext context =
            SecurityContextHolder.createEmptyContext(); // 새로운 Security Context를 생성합니다.
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()); // 인증 정보를 담은 토큰을 생성합니다.
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)); // 토큰에 요청 정보를 설정합니다.
        context.setAuthentication(authToken); // 생성한 Security Context에 인증 토큰을 설정합니다.
        SecurityContextHolder.setContext(context);
      }
    }

    // 다음 필터로 요청과 응답을 전달합니다.
    filterChain.doFilter(request, response);
  }
}
