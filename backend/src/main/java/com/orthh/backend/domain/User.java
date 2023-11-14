package com.orthh.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class User {
        private int id;
        private String email;
        private String password;
        private String nickname;
        private String role;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        @Builder
        public User(String email, String password, String nickname) {
            this.email = email;
            this.password = password;
            this.nickname = nickname;
            this.modifiedDate = LocalDateTime.now();
        }
}