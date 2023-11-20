package com.orthh.backend.repository;

import com.orthh.backend.domain.Product;
import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.product.ProductUserDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {

  // 물품 등록
  public void save(Product request);

  // 등록자정보와 모든 물품 검색
  List<ProductUserDto> findAllProductsWithUser();

  // 상태 변경
  public void changeStatus(Long productid, String status);

  // id 기반으로 물품 조회
  public Product findById(Long productid);

  // 물품명기반으로 물품 조회
  public List<ProductUserDto> findByName(String name);
}
