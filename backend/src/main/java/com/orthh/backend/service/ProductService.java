package com.orthh.backend.service;

import com.orthh.backend.dto.product.ProductSaveReqDto;

public interface ProductService {
  public Long save(ProductSaveReqDto request);
}
