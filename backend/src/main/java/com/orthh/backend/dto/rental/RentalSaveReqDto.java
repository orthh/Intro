package com.orthh.backend.dto.rental;

import com.orthh.backend.domain.Rental;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RentalSaveReqDto {
  private String productId;
  private String orderUserId;
  private String rentDays;
}
