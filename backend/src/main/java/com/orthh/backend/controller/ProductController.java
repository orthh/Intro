package com.orthh.backend.controller;

import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "물품 API", description = "물품 관련 API")
public class ProductController {

  private final ProductService productService;

  @Operation(summary = "물품 등록")
  @PostMapping("/save")
  public ResponseEntity<Long> save(@RequestBody ProductSaveReqDto request) {
    System.out.println(request.getPrice());
    return ResponseEntity.ok(productService.save(request));
  }
}
