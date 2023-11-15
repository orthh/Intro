package com.orthh.backend.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * 'UserDetails' 인터페이스를 구현하여 사용자 인증에 필요한 정보를 제공하는 클래스
 */
@NoArgsConstructor
@Getter
public class User implements UserDetails {

  @Id private Long id;
  private String email;
  private String password;
  private String nickname;
  private String role; // ROLE_USER, ROLE_ADMIN
  private LocalDateTime createdDate;
  private LocalDateTime modifiedDate;

  @Builder
  public User(String email, String password, String nickname, String role) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.modifiedDate = LocalDateTime.now();
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    if (this.role != null) {
      authorities.add(new SimpleGrantedAuthority(this.role)); // 현재 사용자의 권한에 맞는 역할을 부여합니다.
    }
    return authorities;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  // 계정이 만료되지 않았음을 나타내는 메서드
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  // 계정이 잠기지 않았음을 나타내는 메서드
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  // 사용자의 자격 증명(비밀번호)이 만료되지 않았음을 나타내는 메서드
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // 계정이 활성화되어 있음을 나타내는 메서드
  @Override
  public boolean isEnabled() {
    return true;
  }
}
