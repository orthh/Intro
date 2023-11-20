package com.orthh.backend.service;

import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.rental.RentalResDto;
import com.orthh.backend.dto.rental.RentalSaveReqDto;
import java.util.List;

public interface RentalService {
  public Long save(RentalSaveReqDto request);

  public List<RentalResDto> getByUserID(String userId);

  public void patchRental(String rentalId);
}
