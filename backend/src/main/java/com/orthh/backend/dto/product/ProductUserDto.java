package com.orthh.backend.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUserDto {
  private Long productId;
  private String name;
  private String status;
  private String price;
  private Long userId;
  private String nickname;
}

