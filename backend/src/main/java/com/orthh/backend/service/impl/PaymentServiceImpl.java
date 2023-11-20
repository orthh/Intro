package com.orthh.backend.service.impl;

import com.orthh.backend.domain.Payment;
import com.orthh.backend.repository.PaymentRepository;
import com.orthh.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 결제 관련 서비스
 *
 * @author 김혁
 * @since 2023.11.20
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;

  /**
   * post 결제 등록
   *
   * @param rentalid 대여 ID
   * @param amount 결제 금액
   * @param method 결제 방법
   * @return void (이 메서드는 반환 값을 가지지 않습니다.)
   */
  @Override
  public void save(Long rentalid, int amount, String method) {
    paymentRepository.save(
        Payment.builder().rentalid(rentalid).amount(amount).method(method).build());
  }
}
