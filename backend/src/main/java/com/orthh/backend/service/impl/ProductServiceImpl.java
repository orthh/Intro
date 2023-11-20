package com.orthh.backend.service.impl;

import com.orthh.backend.domain.Product;
import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.product.ProductUserDto;
import com.orthh.backend.repository.ProductRepository;
import com.orthh.backend.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 물품 관련 서비스
 *
 * @author 김혁
 * @since 2023.11.19
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  /**
   * 물품 등록
   *
   * @param ProductSaveReqDto
   * @return Long (저장한 물품 id)
   */
  @Override
  public Long save(ProductSaveReqDto request) {
    productRepository.save(request.toEntity());
    return request.toEntity().getProductId();
  }

  /**
   * 모든물품 조회 ( 회원정보와 함께 )
   *
   * @return List<ProductUserDto>
   */
  @Override
  public List<ProductUserDto> findAllProductsWithUser() {
    return productRepository.findAllProductsWithUser();
  }

  /**
   * 현재 상태 변경
   *
   * @param Long productid
   * @param String status
   * @return void
   */
  @Override
  public void changeStatus(Long productid, String status) {
    productRepository.changeStatus(productid, status);
  }

  /**
   * 물품id를 기반으로 물품 검색
   *
   * @param Long productid
   * @return Product
   */
  @Override
  public Product findById(Long productid) {
    return productRepository.findById(productid);
  }

  /**
   * 물품명을 기반으로 물품 검색
   *
   * @param String name
   * @return List<ProductUserDto>
   */
  @Override
  public List<ProductUserDto> findByName(String name) {
    return productRepository.findByName(name);
  }
}
