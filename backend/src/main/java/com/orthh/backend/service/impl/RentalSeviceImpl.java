package com.orthh.backend.service.impl;

import com.orthh.backend.domain.Product;
import com.orthh.backend.domain.Rental;
import com.orthh.backend.dto.rental.RentalResDto;
import com.orthh.backend.dto.rental.RentalSaveReqDto;
import com.orthh.backend.repository.RentalRepository;
import com.orthh.backend.service.PaymentService;
import com.orthh.backend.service.ProductService;
import com.orthh.backend.service.RentalService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 대여 관련 서비스
 *
 * @author 김혁
 * @since 2023.11.20
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class RentalSeviceImpl implements RentalService {

  private final RentalRepository rentalRepository;
  private final ProductService productService;
  private final PaymentService paymentService;

  /**
   * 대여 저장
   *
   * @param RentalSaveReqDto
   * @return Long 저장된 대여id
   */
  @Override
  public Long save(RentalSaveReqDto request) {

    // 상품 조회
    Product product = productService.findById(Long.parseLong(request.getProductId()));

    // 대여 정보 생성
    Rental rental =
        Rental.builder()
            .userid(Long.parseLong(request.getOrderUserId()))
            .productid(Long.parseLong(request.getProductId()))
            .startdate(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
            .duedate(
                LocalDateTime.now(ZoneId.of("Asia/Seoul"))
                    .plusDays(Long.parseLong(request.getRentDays())))
            .returndate(null)
            .build();
    // 대여 등록
    rentalRepository.save(rental);

    // 결제 등록
    paymentService.save(
        rental.getRentalid(), product.getPrice() * Integer.parseInt(request.getRentDays()), "cash");

    // product 상태 변경 (rented)
    productService.changeStatus(Long.parseLong(request.getProductId()), "rented");
    return rental.getRentalid();
  }

  /**
   * 사용자id를 기반으로 대여 조회
   *
   * @param String userId
   * @return List<RentalResDto>
   */
  @Override
  public List<RentalResDto> getByUserID(String userId) {
    return rentalRepository.getByUserID(userId);
  }

  /**
   * 반납
   *
   * @param String rentalId
   * @return void
   */
  @Override
  public void patchRental(String rentalId) {
    // 대여 조회
    Rental rental = rentalRepository.findById(rentalId);

    // 반납일자 업데이트
    rentalRepository.updateReturnDate(
        rental.getRentalid(), LocalDateTime.now(ZoneId.of("Asia/Seoul")));

    // 상품 상태 변경
    productService.changeStatus(rental.getProductid(), "available");
  }
}
