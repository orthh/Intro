package com.orthh.backend.service;

import java.util.List;

import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.product.ProductUserDto;

public interface ProductService {
  public Long save(ProductSaveReqDto request);

  public List<ProductUserDto> findAllProductsWithUser();
}
