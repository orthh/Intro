package com.orthh.backend.service;

import com.orthh.backend.dto.product.ProductSaveReqDto;
import com.orthh.backend.dto.rental.RentalSaveReqDto;

public interface RentalService {
    public Long save(RentalSaveReqDto request);

}
