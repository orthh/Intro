package com.orthh.backend.controller;

import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.product.ProductUserDto;
import com.orthh.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "물품 API", description = "물품 관련 API")
@RestController
public class ProductController {

  private final ProductService productService;

  @Operation(summary = "물품 등록")
  @PostMapping("/save")
  public ResponseEntity<Long> save(@RequestBody ProductSaveReqDto request) {
    return ResponseEntity.ok(productService.save(request));
  }

  @Operation(summary = "물픔 전체 조회")
  @GetMapping("/all")
  public ResponseEntity<List<ProductUserDto>> findAll() {
    return ResponseEntity.ok(productService.findAllProductsWithUser());
  }
}
