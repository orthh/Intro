package com.orthh.backend.repository;

import com.orthh.backend.domain.Payment;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
  // 물품 등록
  public void save(Payment payment);
}
