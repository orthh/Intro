package com.orthh.backend.controller;

import com.orthh.backend.dto.rental.RentalResDto;
import com.orthh.backend.dto.rental.RentalSaveReqDto;
import com.orthh.backend.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 렌탈 관련 컨트롤러
 *
 * @author 김혁
 * @since 2023.11.20
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "RentalAPI", description = "렌탈 관련 API")
@RequestMapping("/rental")
public class RentalController {

  private final RentalService rentalService;

  @Operation(summary = "대여 등록")
  @PostMapping("/save")
  public ResponseEntity<Long> save(@RequestBody RentalSaveReqDto request) {
    log.info("대여 등록 시작 with ProductId = {}", request.getProductId());
    return ResponseEntity.ok(rentalService.save(request));
  }

  @Operation(summary = "대여 조회")
  @GetMapping("/get/{userId}")
  public ResponseEntity<List<RentalResDto>> getByUserID(@PathVariable("userId") String userId) {
    log.info("대여 조회 시작 with UserID = {}", userId);
    if (userId == null) {
      return ResponseEntity.badRequest().build();
    } else {
      return ResponseEntity.ok(rentalService.getByUserID(userId));
    }
  }

  @Operation(summary = "반납하기")
  @PatchMapping("/return/{rentalId}")
  public ResponseEntity<?> patchRental(@PathVariable("rentalId") String rentalId) {
    log.info("반납 시작 with RentalId = {}", rentalId);
    if (rentalId == null) {
      return ResponseEntity.badRequest().build();
    } else {
      rentalService.patchRental(rentalId);
      return ResponseEntity.ok().build();
    }
  }
}
