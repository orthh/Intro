package com.orthh.backend.service.impl;

import com.orthh.backend.domain.Rental;
import com.orthh.backend.dto.rental.RentalSaveReqDto;
import com.orthh.backend.repository.RentalRepository;
import com.orthh.backend.service.RentalService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalSeviceImpl implements RentalService {

  private final RentalRepository rentalRepository;

  /*
   * post 대여 등록
   */
  @Override
  public Long save(RentalSaveReqDto request) {
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
    rentalRepository.save(rental);

    // product 상태 변경 (rented)
    return -1L;
  }
}
