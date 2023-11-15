package com.orthh.backend.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService{
    @Value("${jwt.secret-key}")
    private String jwtSigningKey;

    @Value("${jwt.token.access-expiration-time}")
    private String accesstokenExpireTime;

    // JWT에서 사용자이메일을 추출하는 메서드
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    // 사용자 정보를 바탕으로 JWT를 생성하는 메서드
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // JWT가 유효한지 검증하는 메서드
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // JWT에서 특정 claim을 추출하는 메서드
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    // JWT를 생성하는 메서드
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()) // 클레임과 주제 설정(email)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 발행 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(accesstokenExpireTime))) // 만료 시간설정
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact(); // 서명 키와 알고리즘을 이용해 서명하고, JWT를 생성
    }

    // JWT가 만료되었는지 검사하는 메서드
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // JWT에서 만료 시간을 추출하는 메서드
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // JWT에서 모든 claims를 추출하는 메서드
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    // 서명 키를 가져오는 메서드
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
