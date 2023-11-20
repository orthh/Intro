package com.orthh.backend.service;

import java.time.LocalDateTime;

public interface PaymentService {

  // 결제 등록
  public void save(Long rentalid, int amount, String method);
}
