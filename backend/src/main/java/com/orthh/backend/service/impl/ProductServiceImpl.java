package com.orthh.backend.service.impl;

import com.orthh.backend.domain.Product;
import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.repository.ProductRepository;
import com.orthh.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public Long save(ProductSaveReqDto request) {
    productRepository.save(request.toEntity());
    return request.toEntity().getProductId();
  }
}
