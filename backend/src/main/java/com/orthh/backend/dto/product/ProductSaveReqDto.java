package com.orthh.backend.dto.product;

import com.orthh.backend.domain.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveReqDto {

  private String name;
  private String price;
  private String userid;

  public Product toEntity() {
    return Product.builder()
        .name(this.name)
        .status("available")
        .price(Integer.parseInt(this.price))
        .userid(Long.parseLong(this.userid))
        .build();
  }
}
