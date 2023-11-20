package com.orthh.backend.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Payment {
  private Long rentalid;
  private int amount;
  private String method;
  private LocalDateTime createdDate;

  @Builder
  public Payment(Long rentalid, int amount, String method, LocalDateTime createdDate) {
    this.rentalid = rentalid;
    this.amount = amount;
    this.method = method;
    this.createdDate = LocalDateTime.now();
  }
}
