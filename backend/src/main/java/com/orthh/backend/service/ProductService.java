package com.orthh.backend.service;

import com.orthh.backend.domain.Product;
import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.product.ProductUserDto;
import java.util.List;

public interface ProductService {

  // 상품 등록
  public Long save(ProductSaveReqDto request);

  // 등록자정보와 모든 상품 검색
  public List<ProductUserDto> findAllProductsWithUser();

  // 상태 변경
  public void changeStatus(Long productid, String status);

  // id기반으로 물품 조회
  public Product findById(Long productid);

  // 물품명기반으로 물품 검색
  public List<ProductUserDto> findByName(String name);
}
