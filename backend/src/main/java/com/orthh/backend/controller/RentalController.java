package com.orthh.backend.controller;

import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.rental.RentalSaveReqDto;
import com.orthh.backend.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "RentalAPI", description = "렌탈 관련 API")
@RequestMapping("/rental")
public class RentalController {

  private final RentalService rentalService;

  @Operation(summary = "물품 등록")
  @PostMapping("/save")
  public ResponseEntity<Long> save(@RequestBody RentalSaveReqDto request) {
    return ResponseEntity.ok(rentalService.save(request));
  }
}
