package com.orthh.backend.dto.product;

import com.orthh.backend.domain.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveReqDto {

  String name;
  String price;
  String userid;

  public Product toEntity() {
    return Product.builder()
        .name(this.name)
        .status("available")
        .price(Integer.parseInt(this.price))
        .userId(Long.parseLong(this.userid))
        .build();
  }
}
