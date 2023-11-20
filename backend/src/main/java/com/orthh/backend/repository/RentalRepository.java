package com.orthh.backend.repository;

import com.orthh.backend.domain.Rental;
import com.orthh.backend.dto.rental.RentalResDto;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalRepository {

  // 대여 등록
  public Long save(Rental request);

  // 사용자ID를 기반으로 대여 조회
  public List<RentalResDto> getByUserID(String userId);

  // 대여ID를 기반으로 대여 조회
  public Rental findById(String rentalId);

  public void updateReturnDate(Long rentalId, LocalDateTime returnDate);
}
