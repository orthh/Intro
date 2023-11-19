package com.orthh.backend.repository;

import com.orthh.backend.domain.Product;
import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.product.ProductUserDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {
  public void save(Product request);

  List<ProductUserDto> findAllProductsWithUser();
}
